package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest; 

import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@SpringBootTest 
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository; 

   
    @Test
    public void findByNameShouldReturnCategory() {
        repository.save(new Category("Sci-Fi"));
        
        List<Category> categories = repository.findByName("Sci-Fi");
        
        assertThat(categories).hasSize(1); 
        assertThat(categories.get(0).getName()).isEqualTo("Sci-Fi"); 
    }
    
    
    @Test
    public void createNewCategory() {
        Category category = new Category("Fantasy");
        repository.save(category); 
        
        assertThat(category.getCategoryid()).isNotNull(); 
    }    

    
    @Test
    public void deleteCategory() {
        Category category = new Category("Horror");
        repository.save(category);
        Long id = category.getCategoryid();
        
        repository.deleteById(id); 
        
        assertThat(repository.findById(id)).isEmpty();
    }
}