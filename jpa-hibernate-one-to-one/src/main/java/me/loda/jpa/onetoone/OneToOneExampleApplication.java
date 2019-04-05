package me.loda.jpa.onetoone;
/*******************************************************
 * For Vietnamese readers:
 *    Các bạn thân mến, mình rất vui nếu project này giúp 
 * ích được cho các bạn trong việc học tập và công việc. Nếu 
 * bạn sử dụng lại toàn bộ hoặc một phần source code xin để 
 * lại dường dẫn tới github hoặc tên tác giá.
 *    Xin cảm ơn!
 *******************************************************/

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

/**
 * Copyright 2019 {@author Loda} (https://loda.me).
 * This project is licensed under the MIT license.
 *
 * @since 4/5/2019
 * Github: https://github.com/loda-kun
 */
@SpringBootApplication
@RequiredArgsConstructor
public class OneToOneExampleApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(OneToOneExampleApplication.class, args);
    }

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        Person person = Person.builder()
                              .name("loda")
                              .build();
        personRepository.save(person);
        Address address = Address.builder()
                .city("Hanoi")
                .person(person)
                .build();
        addressRepository.save(address);


    }
}
