package com.softserve.sprint13;

import com.softserve.sprint13.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@SpringBootApplication
@EntityScan("com.software.sprint13.entity")
public class Sprint13HibernateWithSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Sprint13HibernateWithSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running Spring Boot Application");
        try {
            for(int i = 0; i < 10; i++) {
                User user = new User();
                user.setPassword("qwertyQwerty6^");
                user.setRole(User.Role.MENTOR);
                user.setFirstName("MentorName" + i);
                user.setLastName("MentorSurname" + i);
                user.setEmail("mentoruser" + i + "@dh.com");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("com.softserve.sprint13.entity");
        return lef;
    }

}
