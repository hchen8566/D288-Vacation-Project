package com.example.demo;


import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository){
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args)  {

        // Will only populate if the mysql database is refreshed
        if (customerRepository.count() == 1) {

            // customer 1
            Customer hao = new Customer();
            hao.setFirstName("Hao");
            hao.setLastName("Chen");
            hao.setPhone("(843)000-5900");
            hao.setAddress("903 Howard Road");
            hao.setPostal_code("00321");
            hao.setDivision(divisionRepository.getReferenceById(Long.valueOf("3")));
            customerRepository.save(hao);
            // System.out.println("WORKING HAHAHAHA"); Code to test the file

            // customer 2
            Customer luffy = new Customer();
            luffy.setFirstName("Luffy");
            luffy.setLastName("Monkey");
            luffy.setPhone("(902)980-4399");
            luffy.setAddress("123 Grand Line Road");
            luffy.setPostal_code("00323");
            luffy.setDivision(divisionRepository.getReferenceById(Long.valueOf("4")));
            customerRepository.save(luffy);

            // customer 3
            Customer ace = new Customer();
            ace.setFirstName("Ace");
            ace.setLastName("Portugal");
            ace.setPhone("(843)000-3009");
            ace.setAddress("777 White Beard Circle");
            ace.setPostal_code("80328");
            ace.setDivision(divisionRepository.getReferenceById(Long.valueOf("7")));
            customerRepository.save(ace);

            // customer 4
            Customer zoro = new Customer();
            zoro.setFirstName("Zoro");
            zoro.setLastName("Strawhat");
            zoro.setPhone("(843)677-0034");
            zoro.setAddress("123 Lost Lane");
            zoro.setPostal_code("99321");
            zoro.setDivision(divisionRepository.getReferenceById(Long.valueOf("8")));
            customerRepository.save(zoro);

            // customer 1
            Customer nami = new Customer();
            nami.setFirstName("Nami");
            nami.setLastName("Chen");
            nami.setPhone("(843)000-5980");
            nami.setAddress("1 My Waifu Road");
            nami.setPostal_code("696969");
            nami.setDivision(divisionRepository.getReferenceById(Long.valueOf("69")));
            customerRepository.save(nami);

        }


    }
}
