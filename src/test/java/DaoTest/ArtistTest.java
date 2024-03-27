package DaoTest;

import dao.ArtistDao;
import entity.Artist;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArtistTest {

    static ArtistDao artDao;

    @BeforeAll
    static void setup() {
        artDao = new ArtistDao();
    }
    @AfterAll
    static void tearDown() {
        artDao.close();
    }
    @Test
    void testAddArtist() {
        Artist a = new Artist("2", "Adele", LocalDate.of(1988, 5, 5), "https://en.wikipedia.org/wiki/Adele");
        artDao.addArtist(a);
        System.out.println("Them thanh cong");
            }
    @Test
    void testGetAllArtist() {
        artDao.getAllArtist().forEach(System.out::println);
    }
}
