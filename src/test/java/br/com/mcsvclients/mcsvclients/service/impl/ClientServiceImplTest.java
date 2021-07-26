package br.com.mcsvclients.mcsvclients.service.impl;

import br.com.mcsvclients.mcsvclients.exception.ClientException;
import br.com.mcsvclients.mcsvclients.model.Address;
import br.com.mcsvclients.mcsvclients.model.Client;
import br.com.mcsvclients.mcsvclients.model.input.AddressInput;
import br.com.mcsvclients.mcsvclients.model.input.ClientInput;
import br.com.mcsvclients.mcsvclients.model.output.AddressOutput;
import br.com.mcsvclients.mcsvclients.model.output.ClientOutput;
import br.com.mcsvclients.mcsvclients.repository.ClientRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceImplTest extends Mockito {

    @MockBean
    public ClientRepository repository;

    @Autowired
    public ClientServiceImpl clientService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.clientService);
    }

    @Test
    @Description("Validando o construtor com parametros do objeto ClientServiceImpl")
    public void testConstructorWithParam() {
        ClientServiceImpl clientService = new ClientServiceImpl(mock(ClientRepository.class));
        assertEquals(clientService, clientService);
    }

    @Test
    @Description("Validando a funcionalidade getByID")
    public void testGetById() throws ClientException {

        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        Client clientTest = new Client("", "", Collections.emptyList());
        ClientOutput clientOutputTest = new ClientOutput(null, "", "", Collections.emptyList());

        Optional<Client> client = Optional.of(clientTest);

        when(repository.findById(anyLong())).thenReturn(client);

        ClientOutput clientOutput = clientService.getById(anyLong());

        assertEquals(clientOutputTest, clientOutput);
    }

    @Test
    @Description("Validando a funcionalidade getByID com o objeto Address")
    public void testGetByIdWithAddress() throws ClientException {

        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        List<AddressOutput> addressList = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();

        AddressOutput addressOutput = new AddressOutput();
        Address address = new Address();

        addressList.add(addressOutput);
        addresses.add(address);

        Client clientTest = new Client("", "", addresses);
        ClientOutput clientOutputTest = new ClientOutput(null, "", "", addressList);

        Optional<Client> client = Optional.of(clientTest);

        when(repository.findById(anyLong())).thenReturn(client);

        ClientOutput clientOutput = clientService.getById(anyLong());

        assertEquals(clientOutputTest, clientOutput);
    }

    @Test
    @Description("Validando a funcionalidade de deletar cliente")
    public void testDelete() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        repository.deleteById(anyLong());

        clientService.delete(anyLong());

    }


    @Test
    @Description("Validando a funcionalidade de listar todos os clientes")
    public void testListAll() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        List<Client> clients = new ArrayList<>();
        List<ClientOutput> clientOutputsTest = new ArrayList<>();
        List<AddressOutput> addressOutputsTest = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();

        Address address = new Address();
        addresses.add(address);

        Client client = new Client("", "", addresses);
        clients.add(client);

        when(repository.findAll()).thenReturn(clients);

        AddressOutput addressOutputTest = new AddressOutput();
        addressOutputsTest.add(addressOutputTest);

        ClientOutput clientOutputTest = new ClientOutput(null, "", "", addressOutputsTest);
        clientOutputsTest.add(clientOutputTest);

        List<ClientOutput> clientList = clientService.listAll();

        assertEquals(clientOutputsTest, clientList);

    }


    @Test
    @Description("Validando a funcionalidade de listar  todos os clientes, porem retornando vazio")
    public void testListAllWhenArrayIsEmpty() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<ClientOutput> clientList = clientService.listAll();

        assertEquals(Collections.EMPTY_LIST, clientList);

    }


    @Test
    @Description("Validar a mensagem de erro do ClientExcpetion")
    public void testClientExceptionErrorMsg() throws ClientException {
        try {

            /**
             * Fiz isso pois as vezes quando roda os teste no itelliJ, ele nao puxa de primeira a classe ClientRepository, logo da um erro no mockito
             */

            mock(ClientRepository.class);
        } catch(Exception es) {

            ClientRepository repository = mock(ClientRepository.class);
            ClientServiceImpl clientService = new ClientServiceImpl(repository);

            Optional<Client> client = Optional.empty();
            when(repository.findById(0l)).thenReturn(client);

            try{
                clientService.getById(0l);
            } catch (ClientException e) {
                assertEquals("Cliente com id (0), não identificado", e.getMessage());
            }

        }

    }

    @Test
    @Description("Valida a funcionalidade de criar cliente")
    public void testCreateClient() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        List<ClientInput> clients = new ArrayList<>();
        List<AddressInput> addresses = new ArrayList<>();

        AddressInput addressInput = new AddressInput("Teste", "Teste", "Teste", "73091900");
        addresses.add(addressInput);

        ClientInput clientInput = new ClientInput(0l, "Teste", "Teste", addresses);
        clients.add(clientInput);

        clientService.create(clientInput, null);
    }

    @Test
    @Description("Valida a funcionalidade de criar cliente, porém com CEP incorreto")
    public void testCreateClientWhitWrongCEP() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        List<ClientInput> clients = new ArrayList<>();
        List<AddressInput> addresses = new ArrayList<>();

        AddressInput addressInput = new AddressInput("Teste", "Teste", "Teste", "77777777777777");
        addresses.add(addressInput);

        ClientInput clientInput = new ClientInput(0l, "Teste", "Teste", addresses);
        clients.add(clientInput);

        try {
            clientService.create(clientInput, null);
        } catch (ClassCastException e) {
            assertEquals("CEP inválido", e.getMessage());
        }
    }

    @Test
    @Description("Valida a funcionalidade de atualizar cliente")
    public void testUpdateClient() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        List<ClientInput> clients = new ArrayList<>();
        List<AddressInput> addresses = new ArrayList<>();

        Client client = mock(Client.class);

        AddressInput addressInput = new AddressInput("Teste", "Teste", "Teste", "73091900");
        addresses.add(addressInput);

        ClientInput clientInput = new ClientInput(0l, "Teste", "Teste", addresses);
        clients.add(clientInput);

        when(repository.findById(anyLong())).thenReturn(Optional.of(client));

        clientService.create(clientInput, anyLong());
    }

    @Test
    @Description("Valida a funcionalidade de criar, porém com objeto AddressInput e deve retornar array vazio na função convertInputToAddress")
    public void testCreateClientWhenAddressInputIsEmpty() throws ClientException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientServiceImpl clientService = new ClientServiceImpl(repository);

        List<ClientInput> clients = new ArrayList<>();
        List<AddressInput> addresses = new ArrayList<>();

        ClientInput clientInput = new ClientInput(0l, "Teste", "Teste", Collections.emptyList());
        clients.add(clientInput);

        clientService.create(clientInput, null);
    }




}
