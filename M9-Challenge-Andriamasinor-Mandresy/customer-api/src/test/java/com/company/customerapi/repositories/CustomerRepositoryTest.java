package com.company.customerapi.repositories;

import com.company.customerapi.models.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repo;

    @Before
    public void setUp() {
        repo.deleteAll();
    }

    @Test
    @Transactional
    public void shouldAddAndFindById() {

        //create customer
        Customer customer = new Customer();
        customer.setFirst_name("Michel");
        customer.setLast_name("Paragon");
        customer.setEmail("michel@baguette.com");
        customer.setCompany("Baguette&Co");
        customer.setPhone("2029453678");
        customer.setAddress_1("On a hill");
        customer.setAddress_2("In front of the sea");
        customer.setCity("Pain");
        customer.setState("California");
        customer.setPostal_code("760019");
        customer.setCountry("United States");
        //save in database
        repo.save(customer);
        //get customer from database
        Customer fromRepo = repo.findById(1).get();
        //check if it's the correct customer
        assertEquals(1,fromRepo.getId());
        assertEquals("Michel",fromRepo.getFirst_name());
    }

    @Test
    @Transactional
    public void shouldAddAndFindByState() {

        //create customer
        Customer customer = new Customer();
        customer.setFirst_name("Michel");
        customer.setLast_name("Paragon");
        customer.setEmail("michel@baguette.com");
        customer.setCompany("Baguette&Co");
        customer.setPhone("2029453678");
        customer.setAddress_1("On a hill");
        customer.setAddress_2("In front of the sea");
        customer.setCity("Pain");
        customer.setState("California");
        customer.setPostal_code("760019");
        customer.setCountry("United States");

        //create customer 2
        Customer customer2 = new Customer();
        customer2.setFirst_name("Sebastien");
        customer2.setLast_name("Pruneau");
        customer2.setEmail("sebas@baguette.com");
        customer2.setCompany("Baguette&Co");
        customer2.setPhone("2029658678");
        customer2.setAddress_1("On a mountain");
        customer2.setAddress_2("In front of the sea");
        customer2.setCity("Pain");
        customer2.setState("Paris");
        customer2.setPostal_code("760069");
        customer2.setCountry("United States");

        //save in database
        repo.save(customer);
        repo.save(customer2);

        //get from database
        Customer fromRepo = repo.findByState("California").get();
        Customer fromRepo2 = repo.findByState("Paris").get();

        //check if it's the correct customer
        assertEquals("California",fromRepo.getState());
        assertEquals("Michel",fromRepo.getFirst_name());

        assertEquals("Paris",fromRepo2.getState());
        assertEquals("Sebastien",fromRepo2.getFirst_name());
    }

    @Test
    @Transactional
    public void shouldDeleteCustomer() {

        //create customer
        Customer customer2 = new Customer();
        customer2.setFirst_name("Sebastien");
        customer2.setLast_name("Pruneau");
        customer2.setEmail("sebas@baguette.com");
        customer2.setCompany("Baguette&Co");
        customer2.setPhone("2029658678");
        customer2.setAddress_1("On a mountain");
        customer2.setAddress_2("In front of the sea");
        customer2.setCity("Pain");
        customer2.setState("Paris");
        customer2.setPostal_code("760069");
        customer2.setCountry("United States");
        //save customer to database
        repo.save(customer2);
        //delete customer from database
        repo.deleteById(customer2.getId());
        //check if customer is still in database
        Optional<Customer> fromRepo = repo.findById(customer2.getId());
        assertFalse(fromRepo.isPresent());
    }

    @Test
    @Transactional
    public void shouldUpdateCustomer() {

        //create customer
        Customer customer = new Customer();
        customer.setFirst_name("Michel");
        customer.setLast_name("Paragon");
        customer.setEmail("michel@baguette.com");
        customer.setCompany("Baguette&Co");
        customer.setPhone("2029453678");
        customer.setAddress_1("On a hill");
        customer.setAddress_2("In front of the sea");
        customer.setCity("Pain");
        customer.setState("California");
        customer.setPostal_code("760019");
        customer.setCountry("United States");
        //save customer
        repo.save(customer);
        //edit customer
        customer.setFirst_name("Bambino");
        customer.setCountry("Japan");
        //send the change to database
        repo.save(customer);
        //get changed customer from database
        Customer customer1 = repo.getReferenceById(customer.getId());
        //should be the same as the edited version
        assertEquals(customer1,customer);
    }
}