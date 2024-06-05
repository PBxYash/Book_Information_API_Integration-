package com.Book_Information.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String bookIsbn;
    private String title;
    private List<String> publishers;
    private List<Author> authors;
    private int totalPages;
    private String publishedDate;


}

