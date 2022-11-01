package repositories;

import com.company.Summative2AndriamasinoroMandresy.models.Author;
import com.company.Summative2AndriamasinoroMandresy.models.Book;
import com.company.Summative2AndriamasinoroMandresy.models.Publisher;
import com.company.Summative2AndriamasinoroMandresy.repositories.AuthorRepository;
import com.company.Summative2AndriamasinoroMandresy.repositories.BookRepository;
import com.company.Summative2AndriamasinoroMandresy.repositories.PublisherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    AuthorRepository authorRepo;
    @Autowired
    PublisherRepository publisherRepo;
    @Autowired
    BookRepository bookRepo;

    @Before
    public void setUp() throws Exception {
        authorRepo.deleteAll();
        publisherRepo.deleteAll();
        bookRepo.deleteAll();
    }

    @Test
    public void addGetDeleteBook() {
        //create Author, Publisher then book
        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        authorRepo.save(author);

        //add new publisher
        Publisher publisher = new Publisher();
        publisher.setName("Cris");
        publisher.setStreet("Real st");
        publisher.setCity("Madrida");
        publisher.setState("NV");
        publisher.setPostal_code("4698");
        publisher.setPhone("5423010259");
        publisher.setEmail("cr8@gmail.com");
        publisherRepo.save(publisher);

        //add new Book
        Book book = new Book();
        book.setIsbn("1852860820");
        book.setPublish_date(LocalDate.of(1986,5,6));
        System.out.println(author.getId());
        book.setAuthorId(author.getId());
        book.setTitle("Eagleman vs Greatman");
        book.setPublisherId(publisher.getId());
        book.setPrice(BigDecimal.valueOf(259.99));
        bookRepo.save(book);

        //get book by id
        Optional<Book> book1 = bookRepo.findById(book.getId());

        //assert that we get correct author
        assertEquals(book1.get(), book);

        //delete author
        bookRepo.deleteById(book.getId());

        //assert that author was deleted
        book1 = bookRepo.findById(book.getId());
        assertFalse(book1.isPresent());

    }

    @Test
    public void getAllBooks() {

        //create Author, Publisher then book
        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        authorRepo.save(author);

        //add new publisher
        Publisher publisher = new Publisher();
        publisher.setName("Cris");
        publisher.setStreet("Real st");
        publisher.setCity("Madrida");
        publisher.setState("NV");
        publisher.setPostal_code("4698");
        publisher.setPhone("5423010259");
        publisher.setEmail("cr8@gmail.com");
        publisherRepo.save(publisher);

        //add new Book
        Book book = new Book();
        book.setIsbn("1852860820");
        book.setPublish_date(LocalDate.of(1986,5,6));
        book.setAuthorId(author.getId());
        book.setTitle("Eagleman vs Greatman");
        book.setPublisherId(publisher.getId());
        book.setPrice(BigDecimal.valueOf(259.99));
        bookRepo.save(book);

        //create 2nd book
        //add new author
        Author author1 = new Author();
        author1.setFirstName("Bastien");
        author1.setLast_name("Smolov");
        author1.setStreet("Brada st");
        author1.setCity("Citro");
        author1.setState("MA");
        author1.setPostal_code("21685");
        author1.setPhone("4620153904");
        author1.setEmail("bastien@gmail.com");
        authorRepo.save(author1);

        //add new publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Neymoor");
        publisher1.setStreet("Barca st");
        publisher1.setCity("Lona st");
        publisher1.setState("NY");
        publisher1.setPostal_code("7915");
        publisher1.setPhone("4567891234");
        publisher1.setEmail("nm8@gmail.com");
        publisherRepo.save(publisher1);

        //add new Book
        Book book1 = new Book();
        book1.setIsbn("9781794798939");
        book1.setPublish_date(LocalDate.of(1987, Month.JULY,27));
        book1.setAuthorId(author1.getId());
        book1.setTitle("Never Gona Abandon You");
        book1.setPublisherId(publisher1.getId());
        book1.setPrice(BigDecimal.valueOf(542.65));
        bookRepo.save(book1);

        //get all authors
        List<Book> bookList = bookRepo.findAll();

        //assert all authors were retrieved
        assertEquals(bookList.size(), 2);

    }

    @Test
    public void updateBook() {

        //create Author, Publisher then book
        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        authorRepo.save(author);

        //add new publisher
        Publisher publisher = new Publisher();
        publisher.setName("Cris");
        publisher.setStreet("Real st");
        publisher.setCity("Madrida");
        publisher.setState("NV");
        publisher.setPostal_code("4698");
        publisher.setPhone("5423010259");
        publisher.setEmail("cr8@gmail.com");
        publisherRepo.save(publisher);

        //add new Book
        Book book = new Book();
        book.setIsbn("1852860820");
        book.setPublish_date(LocalDate.of(1986,5,6));
        book.setAuthorId(author.getId());
        book.setTitle("Eagleman vs Greatman");
        book.setPublisherId(publisher.getId());
        book.setPrice(BigDecimal.valueOf(259.99));
        bookRepo.save(book);

        //update title
        book.setTitle("The Revengers");
        bookRepo.save(book);

        //assert that book was updated
        Optional<Book> book1 = bookRepo.findById(book.getId());
        assertEquals(book1.get(), book);
    }

    @Test
    public void findByAuthor() {
        //add new author
        Author author = new Author();
        author.setFirstName("Baloo");
        author.setLast_name("Bilbaolin");
        author.setStreet("Croissant st");
        author.setCity("Alexandria");
        author.setState("AL");
        author.setPostal_code("73194");
        author.setPhone("4568762014");
        author.setEmail("baloo@gmail.com");
        authorRepo.save(author);

        //add new author
        Author author1 = new Author();
        author1.setFirstName("Cris");
        author1.setLast_name("Tiano");
        author1.setStreet("Real st");
        author1.setCity("Madrida");
        author1.setState("NV");
        author1.setPostal_code("4698");
        author1.setPhone("5423010259");
        author1.setEmail("cr8@gmail.com");
        authorRepo.save(author1);

        //add new publisher
        Publisher publisher1 = new Publisher();
        publisher1.setName("Neymoor");
        publisher1.setStreet("Barca st");
        publisher1.setCity("Lona st");
        publisher1.setState("MA");
        publisher1.setPostal_code("7915");
        publisher1.setPhone("4567891234");
        publisher1.setEmail("nm8@gmail.com");
        publisherRepo.save(publisher1);

        //add 1st book written by author
        Book book = new Book();
        book.setIsbn("1852860820");
        book.setPublish_date(LocalDate.of(1986,5,6));
        book.setAuthorId(author.getId());
        book.setTitle("Eagleman vs Greatman");
        book.setPublisherId(publisher1.getId());
        book.setPrice(BigDecimal.valueOf(259.99));
        bookRepo.save(book);

        //add 2nd Book written by author1
        Book book1 = new Book();
        book1.setIsbn("4935860820");
        book1.setPublish_date(LocalDate.of(2022,7,21));
        book1.setAuthorId(author1.getId());
        book1.setTitle("The Starving Games");
        book1.setPublisherId(publisher1.getId());
        book1.setPrice(BigDecimal.valueOf(69.99));
        bookRepo.save(book1);

        //add 3rd Book written by author1
        Book book2 = new Book();
        book2.setIsbn("6317760820");
        book2.setPublish_date(LocalDate.of(1786,4,15));
        book2.setAuthorId(author1.getId());
        book2.setTitle("How to sleep 8 hours in 1 hour");
        book2.setPublisherId(publisher1.getId());
        book2.setPrice(BigDecimal.valueOf(299.99));
        bookRepo.save(book2);

        //assert that there is one book by author
        List<Book> bookList = bookRepo.findByAuthorId(author.getId());
        assertEquals(bookList.size(), 1);

        //assert that there are two book by author1
        bookList = bookRepo.findByAuthorId(author1.getId());
        assertEquals(bookList.size(), 2);
    }
}