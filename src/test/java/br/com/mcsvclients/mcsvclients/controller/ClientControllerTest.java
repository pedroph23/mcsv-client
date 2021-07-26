package br.com.mcsvclients.mcsvclients.controller;

import br.com.mcsvclients.mcsvclients.exception.ClientException;
import br.com.mcsvclients.mcsvclients.exception.Error;
import br.com.mcsvclients.mcsvclients.model.Client;
import br.com.mcsvclients.mcsvclients.model.input.AddressInput;
import br.com.mcsvclients.mcsvclients.model.input.ClientInput;
import br.com.mcsvclients.mcsvclients.model.output.ClientOutput;
import br.com.mcsvclients.mcsvclients.model.output.DataResponse;
import br.com.mcsvclients.mcsvclients.service.ClientService;
import br.com.mcsvclients.mcsvclients.service.impl.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;


@WebMvcTest(ClientController.class)
public class ClientControllerTest extends Mockito {

    @Autowired
    public ClientController clientController;

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public ClientService clientService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.clientController);
    }

    @Test
    @Description("Validando funcionalidade de listagem de cliente retornando status 200")
    public void testListAllStatusOK() throws Exception {

        clientService = mock(ClientService.class);

        when(clientService.listAll()).thenReturn(Collections.EMPTY_LIST);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(new DataResponse(Collections.EMPTY_LIST))));

    }

    @Test
    @Description("Validando funcionalidade de listagem de cliente retornando status 500")
    public void testListAllStatus500() {

        clientService = mock(ClientService.class);

        ClientController controller = new ClientController(clientService);
        when(clientService.listAll()).thenThrow(HttpServerErrorException.InternalServerError.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.listAll().getStatusCode());
        assertEquals(new DataResponse<>(new Error("Erro interno. Por favor aguarde!")), controller.listAll().getBody());

    }

    @Test
    @Description("Validando a funcionalidade busca por um cliente pasando o parametro id retornando status 204")
    public void testGetByIdWithStatus204() throws Exception {
        List<ClientOutput> list =  new ArrayList<>();

        clientService = mock(ClientService.class);


        ClientOutput clientOutput = new ClientOutput();
        list.add(clientOutput);

        when(clientService.getById(anyLong())).thenReturn(clientOutput);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/client/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @Description("Validando a funcionalidade busca por um cliente pasando o parametro id, retornando status 200")
    public void testGetByIdWithStatus200() throws Exception {

        clientService = mock(ClientService.class);
        ClientOutput clientOutput = mock(ClientOutput.class);

        ClientController controller = new ClientController(clientService);

        when(clientService.getById(anyLong())).thenReturn(clientOutput);

        ResponseEntity responseEntity = controller.getById(anyLong());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(clientOutput), responseEntity.getBody());

    }

    @Test
    @Description("Validando a funcionalidade busca por um cliente pasando o parametro id, retornando status 400")
    public void testGetByIdWithStatus400() throws Exception {

        clientService = mock(ClientService.class);

        ClientController controller = new ClientController(clientService);

        when(clientService.getById(anyLong())).thenThrow(ClientException.class);

        ResponseEntity responseEntity = controller.getById(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error(null)), responseEntity.getBody());

    }

    @Test
    @Description("Validando a funcionalidade busca por um cliente pasando o parametro id, retornando status 500")
    public void testGetByIdWithStatus500() throws Exception {

        clientService = mock(ClientService.class);

        ClientController controller = new ClientController(clientService);
        when(clientService.getById(anyLong())).thenThrow(HttpServerErrorException.InternalServerError.class);

        ResponseEntity responseEntity = controller.getById(anyLong());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error("Erro interno. Por favor aguarde!")), responseEntity.getBody());

    }

    @Test
    @Description("Validando a funcionalidade remocao por um cliente pasando o parametro id, retornando status 200")
    public void testDeleteWithStatus200() throws Exception {

        clientService = mock(ClientService.class);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/v1/client/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Description("Validando a funcionalidade remocao por um cliente pasando o parametro id, retornando status 400")
    public void testDeleteWithStatus400() throws Exception {

        clientService = mock(ClientService.class);

        ClientController controller = new ClientController(clientService);

        doThrow(EmptyResultDataAccessException.class).when(clientService).delete(anyLong());

        ResponseEntity responseEntity = controller.delete(anyLong());

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }


    @Test
    @Description("Validando a funcionalidade remocao por um cliente pasando o parametro id, retornando status 500")
    public void testDeleteWithStatus500() throws Exception {

        clientService = mock(ClientService.class);

        ClientController controller = new ClientController(clientService);

        doThrow(HttpServerErrorException.InternalServerError.class).when(clientService).delete(anyLong());

        ResponseEntity responseEntity = controller.delete(anyLong());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error("Erro interno. Por favor aguarde!")), responseEntity.getBody());

    }

    @Test
    @Description("Validando a funcionalidade criacao de um cliente, retornando status 201")
    public void testCreateWithStatus201() throws Exception {

        List<AddressInput> list = new ArrayList<>();
        list.add(new AddressInput());

        ClientInput clientInput = new ClientInput(0l, "teste", "teste", list);

        clientService = mock(ClientService.class);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/v1/client")
                        .content(asJsonString(clientInput))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @Description("Validando a funcionalidade criacao de um cliente, retornando status 400")
    public void testCreateWithStatus400() throws Exception {

        List<AddressInput> list = new ArrayList<>();
        list.add(new AddressInput());

        ClientInput clientInput = new ClientInput(0l, "teste", "teste", list);

        clientService = mock(ClientService.class);
        ClientController controller = new ClientController(clientService);

        doThrow(ClassCastException.class).when(clientService).create(clientInput, null);

        ResponseEntity responseEntity = controller.create(clientInput);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error(null)), responseEntity.getBody());

    }

    @Test
    @Description("Validando a funcionalidade criacao de um cliente, retornando status 500")
    public void testCreateWithStatus500() throws ClientException {

        List<AddressInput> list = new ArrayList<>();
        list.add(new AddressInput());

        ClientInput clientInput = new ClientInput(0l, "teste", "teste", list);

        clientService = mock(ClientService.class);
        ClientController controller = new ClientController(clientService);

        doThrow(HttpServerErrorException.InternalServerError.class).when(clientService).create(clientInput, null);

        ResponseEntity responseEntity = controller.create(clientInput);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error("Erro interno. Por favor aguarde!")), responseEntity.getBody());

    }

    @Test
    @Description("Validando a funcionalidade atualização de um cliente, retornando status 200")
    public void testUpdateWithStatus200() throws Exception {

        List<AddressInput> list = new ArrayList<>();
        list.add(new AddressInput());

        ClientInput clientInput = new ClientInput(0l, "teste", "teste", list);

        clientService = mock(ClientService.class);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/v1/client/0")
                        .content(asJsonString(clientInput))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Description("Validando a funcionalidade atualização de um cliente, retornando status 400")
    public void testUpdateWithStatus400() throws Exception {

        List<AddressInput> list = new ArrayList<>();
        list.add(new AddressInput());

        ClientInput clientInput = new ClientInput(0l, "teste", "teste", list);

        clientService = mock(ClientService.class);
        ClientController controller = new ClientController(clientService);

        doThrow(ClassCastException.class).when(clientService).create(clientInput, 0l);

        ResponseEntity responseEntity = controller.update(clientInput, 0l);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error(null)), responseEntity.getBody());

    }


    @Test
    @Description("Validando a funcionalidade atualização de um cliente, retornando status 500")
    public void testUpdateWithStatus500() throws Exception {

        List<AddressInput> list = new ArrayList<>();
        list.add(new AddressInput());

        ClientInput clientInput = new ClientInput(0l, "teste", "teste", list);

        clientService = mock(ClientService.class);
        ClientController controller = new ClientController(clientService);

        doThrow(HttpServerErrorException.InternalServerError.class).when(clientService).create(clientInput, 0l);

        ResponseEntity responseEntity = controller.update(clientInput, 0l);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(new DataResponse<>(new Error("Erro interno. Por favor aguarde!")), responseEntity.getBody());

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
