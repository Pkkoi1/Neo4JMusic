package dao;

import entity.Artist;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import util.AppUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistDao {
    Driver driver;

    public ArtistDao() {
        driver = AppUtils.driver();
    }

    public void close() {
        driver.close();
    }
    public Boolean addArtist(Artist a)
    {
        String query = "CREATE (a:Artist {id: $id, name: $name, birthDate: datetime($birthDate), url:$url})";
        Map<String, Object> params = Map.of("id", a.getId(), "name", a.getName(), "birthDate", a.getBirthDate().atTime(0, 0, 0), "url", a.getUrl());
        try(Session session = driver.session())
        {
            return session.executeWrite(tx -> {
                tx.run(query, params).consume();
                return true;
            });
        }
    }
    public List<Artist> getAllArtist()
    {
        String query = "MATCH (a:Artist) RETURN a";
        Map<String, Object> params = new HashMap<>();
        Artist artist = new Artist();
        try(Session session = driver.session())
        {
            return session.executeRead(tx -> {
                Result result = tx.run(query, params);
                artist.setId(result.next().get("a").get("id").asString());
                artist.setName(result.next().get("a").get("name").asString());
                artist.setBirthDate(result.next().get("a").get("birthDate").asZonedDateTime().toLocalDate());
                artist.setUrl(result.next().get("a").get("url").asString());
                return List.of(artist);
            });
        }
    }


}
