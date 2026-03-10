package fi.haagahelia.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner BooksDemo(BookRepository repository, CategoryRepository categoryRepository, UserRepository uRepository) {
        return (args) -> {
            log.info("Save some books");
            Category fiction = new Category("Fiction");
            Category educational = new Category("Educational");
            Category mystery = new Category("Mystery");

            categoryRepository.save(fiction);
            categoryRepository.save(educational);
            categoryRepository.save(mystery);

            repository.save(new Book("Harry Potter", "JK Rowling", "13701", 100, 2001, fiction));
            repository.save(new Book("suomen Mestari", "Unknown", "77711", 50, 2026, mystery));
            repository.save(new Book("Introduction to java", "Richard stallman", "12345", 200, 1984, educational));


            User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@bookstore.com", "USER");
            
            // "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C" = "admin"
            User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@bookstore.com", "ADMIN");
            
            uRepository.save(user1);
            uRepository.save(user2);

            log.info("fetch all books");
            for (Book book : repository.findAll()) {
                log.info(book.toString());
            }

            log.info("fetch an individual book by id");
            for (Book book : repository.findByIsbn("13701")) {
                log.info(book.toString());
            }
        };
    }
}