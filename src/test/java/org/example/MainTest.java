package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Service.userService;
import org.example.config.securityConfig;
import org.example.controller.userController;
import org.example.model.TODO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(userController.class)
@Import(securityConfig.class)
public class MainTest
{
    @MockBean
    private userService us;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGet() throws Exception
    {
        List<TODO> todos = List.of(new TODO("practice guitar","go to class at 6:30", LocalDateTime.now()),new TODO("Read book","then she was gone",LocalDateTime.now()));
        when(us.getAllTodos()).thenReturn(todos);
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }
    // arrange,act,assert
    @Test
    void testPost() throws Exception
    {
        TODO input = new TODO("practice coding","on spring boot",LocalDateTime.now());
        TODO saved = new TODO("practice coding","on spring boot",LocalDateTime.now());

        when(us.createTodo(input)).thenReturn(saved);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(input)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("practice coding"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("on spring boot"));
    }

}
