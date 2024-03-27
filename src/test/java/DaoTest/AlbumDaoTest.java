package DaoTest;

import dao.AlbumDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AlbumDaoTest {
     static AlbumDao albumDao;

     @BeforeAll
        static void setup() {
            albumDao = new AlbumDao();
        }

        @AfterAll
    static void tearDown() {
            albumDao.close();
        }

        @Test
    void testChange()
    {
        albumDao.updatePrice("A001", 1000.0);
    }
    @Test
    void testFind()
    {
        System.out.println(albumDao.findAlbumByArtist("Rock"));
    }
    @Test
    void testFindList()
    {
//        getNumberOfAlbumByGenre
        albumDao.getNumberOfAlbumByGenre().forEach((k,v)-> System.out.println(k + " có " + v + " album"));
    }

}
