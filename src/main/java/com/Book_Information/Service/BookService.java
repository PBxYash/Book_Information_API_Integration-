package com.Book_Information.Service;


import com.Book_Information.Entity.Author;
import com.Book_Information.Entity.BookResponse;
import com.Book_Information.Exception.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private RestTemplate restTemplate;
    public BookResponse getBookDetails(String isbn) throws Exception {
        String url = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&jscmd=data&format=json";

        String jsonResponse = restTemplate.getForObject(url, String.class);

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            throw new CustomException("No data found for ISBN: " + isbn);
        }

        JsonNode root = new ObjectMapper().readTree(jsonResponse);
        JsonNode bookNode = root.path("ISBN:" + isbn);

        if (bookNode.isMissingNode()) {
            throw new CustomException("No data found for ISBN: " + isbn);
        }

        BookResponse response = new BookResponse();
        response.setBookIsbn(isbn);
        response.setTitle(bookNode.path("title").asText());
        response.setPublishers(parsePublishers(bookNode));
        response.setAuthors(parseAuthors(bookNode));
        response.setTotalPages(bookNode.path("number_of_pages").asInt());
        response.setPublishedDate(bookNode.path("publish_date").asText());

        return response;
    }

    private List<String> parsePublishers(JsonNode bookNode) {
        return bookNode.path("publishers").findValuesAsText("name");
    }

    private List<Author> parseAuthors(JsonNode bookNode) {
        return bookNode.path("authors").findValuesAsText("name").stream()
                .map(name -> new Author(name))
                .collect(Collectors.toList());
    }
}

