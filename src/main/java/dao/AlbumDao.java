package dao;

import entity.Album;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import util.AppUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AlbumDao {
    Driver driver;

    public AlbumDao() {
        driver = AppUtils.driver();
    }

    public void close() {
        driver.close();
    }

    public boolean updatePrice(String id, Double price)
    {
        String query = "MATCH (a:Album {id: $id}) SET a.price = $price";
        Map<String, Object> params = Map.of("id", id, "price", price);
        try(Session session = driver.session())
        {
            return session.executeWrite(tx -> {
                tx.run(query, params).consume();
                return true;
            });
        }

    }
    public List<Album> findAlbumByArtist(String name)
    {
        String query = "MATCH (al:Album) - [:BELONG_TO] -> (g:Genre) WHERE g.name = $name RETURN al";
        Map<String, Object> params = Map.of("name", name);
        try(Session session = driver.session())
        {
            return session.executeRead(tx -> {
                return tx.run(query, params).list(r -> AppUtils.nodeToPOJO(r.get("al").asNode(), Album.class));
            });
        }
    }
    public Map<String,Long> getNumberOfAlbumByGenre()
    {
        String query = "MATCH (al:Album) - [:BELONG_TO] -> (g:Genre) RETURN g.name as genre, count(al) as number";
        Map<String, Object> params = Map.of();
        try(Session session = driver.session())
        {
            return session.executeRead(tx -> {
                return tx.run(query, params).stream()
                        .collect(
                                Collectors.toMap(
                                        record -> record.get("genre").asString(),
                                        record -> record.get("number").asLong(),
                                        (a, b) -> a,
                                        LinkedHashMap::new
                                )
                        );
            });
        }
    }

}
