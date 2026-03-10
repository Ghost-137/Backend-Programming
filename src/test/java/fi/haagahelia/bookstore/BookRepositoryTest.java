package fi.haagahelia.bookstore;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    public void findByTitleShouldReturnBook() {
        
        repository.save(new Book("Test Title", "Test Author", "20135", 20.0, 2026, null));
        List<Book> books = repository.findByIsbn("20135");
        assertThat(books).hasSize(1); 
        assertThat(books.get(0).getAuthor()).isEqualTo("Test Author");
    }
    
    @Test
    public void createNewBook() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);
        Book book = new Book("New Author", "New Title", "54321", 25.0, 2025, category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        Book book = new Book("Delete Author", "Delete Title", "67890", 15.0, 2024, null);
        repository.save(book);
        Long id = book.getId();
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }





}
