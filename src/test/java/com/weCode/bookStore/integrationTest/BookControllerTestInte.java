package com.weCode.bookStore.integrationTest;

import com.weCode.bookStore.BookStoreApplication;

import com.weCode.bookStore.config.PasswordConfig;

import com.weCode.bookStore.dto.BookDto;
import com.weCode.bookStore.model.Usuario;

import com.weCode.bookStore.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;


import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BookStoreApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTestInte {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired

    private PasswordConfig passwordEncoder;

    void SetUpHeader(){

        Usuario user = new Usuario();
        user.setUsername("leoc@gmail.com");
        user.setPassword(passwordEncoder.getPasswordEncoder().encode("password123"));
        user.setId(UUID.fromString("c0ff2db4-f9c5-4222-8469-0195ba9a0f0e"));

        String token = jwtUtil.generateToken(user);

        testRestTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList(((request, body, execution) -> {
                   request.getHeaders()
                           .add("Authorization", "Bearer " + token);
                   return execution.execute(request, body);

                }))
        );


    }





    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void shouldReturnBooksWhenBookApiCalled() {
        SetUpHeader();

        BookDto[] listOfBooks = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/books", BookDto[].class);
        assertThat(listOfBooks).isNotNull();
        assertThat(listOfBooks.length).isEqualTo(18);

    }

    /*@Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void shouldReturnBooksWhenBookApiCalled1( ) {

        BookDto[] listOfBooks = testRestTemplate.getForObject("http://localhost:" + port + "/api/v1/books", BookDto[].class);
        assertThat(listOfBooks).isNotNull();
        assertThat(listOfBooks.length).isEqualTo(2);

    }

    @Test
    @Sql(scripts = {"classpath:InsertInitialBookRecordForTest.sql"})
    void ShouldReturnOneBookWhenCalledWithTestTitle() {
        BookDto[] listOfBooks = testRestTemplate.getForObject( "http://localhost:" + port + "/api/v1/books/Test repository", BookDto[].class);
        assertThat(listOfBooks).isNotNull();
        assertThat(listOfBooks.length).isEqualTo(1);


    }*/
}