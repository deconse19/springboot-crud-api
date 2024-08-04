package springboot.crud.practice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.crud.practice.entity.Book;
import springboot.crud.practice.repo.BookRepo;

@RestController
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/showAllBooks")
    public ResponseEntity<List<Book>> showAllBooks() {
        try {
            List<Book> bookList = new ArrayList<>();
            bookRepo.findAll().forEach(bookList::add);

            if (bookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/showBookById/{id}")
    public ResponseEntity<Book> showBookById(@PathVariable Long id) {
        Optional<Book> bookData = bookRepo.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book bookObj = bookRepo.save(book);

        return new ResponseEntity<>(bookObj, HttpStatus.OK);
    }

    @PostMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBook) {
        Optional<Book> oldBookData = bookRepo.findById(id);

        if (oldBookData.isPresent()) {
            Book updatedBookData = oldBookData.get();
            updatedBookData.setName(newBook.getName());
            updatedBookData.setAuthor(newBook.getAuthor());
            updatedBookData.setDatePublished(newBook.getDatePublished());

            Book bookObj = bookRepo.save(updatedBookData);
            return new ResponseEntity<>(bookObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
        bookRepo.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
