package repositories;

import com.company.Summative2AndriamasinoroMandresy.models.Publisher;
import com.company.Summative2AndriamasinoroMandresy.repositories.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherRepositoryTest {
    @Autowired
    PublisherRepository repo;

    @Before
    public void setUp() throws Exception {
        repo.deleteAll();
    }

    @Test
    public void addGetDeletePublisher() {
        //add new publisher
        Publisher publisher = new Publisher();
        publisher.setName("Baloo");
        publisher.setStreet("Croissant st");
        publisher.setCity("Alexandria");
        publisher.setState("AL");
        publisher.setPostal_code("73194");
        publisher.setPhone("4568762014");
        publisher.setEmail("baloo@gmail.com");
        repo.save(publisher);

        //get publisher by id
        Optional<Publisher> publisher1 = repo.findById(publisher.getId());

        //assert that we get correct publisher
        assertEquals(publisher1.get(), publisher);

        //delete publisher
        repo.deleteById(publisher.getId());

        //assert that publisher was deleted
        publisher1 = repo.findById(publisher.getId());
        assertFalse(publisher1.isPresent());
    }

    @Test
    public void getAllPublishers() {

        //add new publisher
        Publisher publisher = new Publisher();
        publisher.setName("Baloo");
        publisher.setStreet("Croissant st");
        publisher.setCity("Alexandria");
        publisher.setState("AL");
        publisher.setPostal_code("73194");
        publisher.setPhone("4568762014");
        publisher.setEmail("baloo@gmail.com");
        repo.save(publisher);

        //add new publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Cris");
        publisher1.setStreet("Real st");
        publisher1.setCity("Madrida");
        publisher1.setState("NV");
        publisher1.setPostal_code("4698");
        publisher1.setPhone("5423010259");
        publisher1.setEmail("cr8@gmail.com");
        repo.save(publisher1);

        //get all publishers
        List<Publisher> publisherList = repo.findAll();

        //assert all publishers were retrieved
        assertEquals(publisherList.size(), 2);

    }

    @Test
    public void updateLabel() {

        //add new publisher
        Publisher publisher = new Publisher();
        publisher.setName("Baloo");
        publisher.setStreet("Croissant st");
        publisher.setCity("Alexandria");
        publisher.setState("AL");
        publisher.setPostal_code("73194");
        publisher.setPhone("4568762014");
        publisher.setEmail("baloo@gmail.com");
        repo.save(publisher);

        //update name and city
        publisher.setName("Chinpin");
        publisher.setCity("Fredericksboulevard");
        repo.save(publisher);

        //assert that publisher was updated
        Optional<Publisher> publisher1 = repo.findById(publisher.getId());
        assertEquals(publisher1.get(), publisher);
    }
}