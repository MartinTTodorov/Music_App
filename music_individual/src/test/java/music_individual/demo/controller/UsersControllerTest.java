package music_individual.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import music_individual.demo.business.IUsersManager;
import music_individual.demo.domain.CreateUserRequest;
import music_individual.demo.domain.UpdateUserRequest;
import music_individual.demo.persistence.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UsersController.class})
@ExtendWith(SpringExtension.class)
class UsersControllerTest {
    @Autowired
    private UsersController usersController;

    @MockBean
    private IUsersManager iUsersManager;



    @Test
    void GetAllUsers() throws Exception {
        when(iUsersManager.GetAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void CreateUser() throws Exception {
        when(iUsersManager.GetAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CreateUserRequest("test", "1234")));
        MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void DeleteUser() throws Exception {
        when(iUsersManager.DeleteUser((Integer) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{userId}", 123);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }




    @Test
    void UserConverter() {
        UserEntity actualUserConverterResult = usersController
                .UserConverter(new CreateUserRequest("test", "1234"));
        assertNull(actualUserConverterResult.getEmail());
        assertEquals("test", actualUserConverterResult.getUsername());
        assertEquals("Listener", actualUserConverterResult.getRole());
        assertEquals("1234", actualUserConverterResult.getPassword());
        assertNull(actualUserConverterResult.getId());
    }



    @Test
    void UpdateUser() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/users/{id}", "Uri Variables", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UpdateUserRequest(1, "1234", "testUser@example.org")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(usersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

