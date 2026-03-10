package fi.haagahelia.bookstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class BookRestTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
    
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                                      .apply(springSecurity())
                                      .build();
    }

    
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testApiBooksEndpoint() throws Exception {
        this.mockMvc.perform(get("/api/books"))
                    .andExpect(status().isOk());
    }
}