package br.com.mcsvclients.mcsvclients.model;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest extends Mockito {

    @Test
    @Description("Validando o construtor do objeto ClientTest")
    public void testConstructor() {
        Client client = new Client();
        assertEquals(client, client);
    }

    @Test
    @Description("Validando o construtor do objeto ClientTest")
    public void testConstructorWithValues() {
        Client client = new Client(anyString(), anyString(), anyList());
        assertEquals(client, client);
    }

    @Test
    @Description("Validando a funcionalidade getId and setId do objeto Client")
    public void testGetAndSetId() {
        Client client = new Client();
        client.setId(anyLong());
       assertEquals(anyLong(), client.getId());
    }

    @Test
    @Description("Validando a funcionalidade getName and setName do objeto Client")
    public void testGetAndSetName() {
        Client client = new Client();
        client.setName(anyString());
        assertEquals(anyString(), client.getName());
    }

    @Test
    @Description("Validando a funcionalidade setName e getName do objeto Client")
    public void testGetAndSetStreet() {
        Client client = new Client();
        client.setName(anyString());
        assertEquals("", client.getName());
    }

    @Test
    @Description("Validando a funcionalidade getCpf and setCpf do objeto Client")
    public void testGetAndSetCpf() {
        Client client = new Client();
        client.setCpf(anyString());
        assertEquals(anyString(), client.getCpf());
    }

    @Test
    @Description("Validando a funcionalidade setAddress and getAddress do objeto Client")
    public void testSetAndGetAddress() {
        Client client = new Client();
        client.setAddresses(anyList());
        assertEquals(anyList(), client.getAddresses());
    }


}
