package br.com.mcsvclients.mcsvclients.model.input;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressInputTest extends Mockito {
    @Test
    @Description("Validando o construtor do objeto AddressInput")
    public void testConstructor() {
        AddressInput address = new AddressInput();
        assertEquals(address, address);
    }

    @Test
    @Description("Validando a funcionalidade getId and setId do objeto AddressInput")
    public void testGetAndSetCity() {
        AddressInput address = new AddressInput();
        address.setCity("");
        assertEquals("", address.getCity());
    }

    @Test
    @Description("Validando a funcionalidade getSreet and setStreet do objeto AddressInput")
    public void testGetAndSetStreet() {
        AddressInput address = new AddressInput();
        address.setStreet("");
        assertEquals("", address.getStreet());
    }

    @Test
    @Description("Validando a funcionalidade getCep and setCep do objeto AddressInput")
    public void testGetAndSetCep() {
        AddressInput address = new AddressInput();
        address.setCep("");
        assertEquals("", address.getCep());
    }


    @Test
    @Description("Validando a funcionalidade setStreet and getStreet do objeto AddressInput")
    public void testSetAndGetCity() {
        AddressInput address = new AddressInput();
        address.setStreet(anyString());
        assertEquals(anyString(), address.getStreet());
    }

    @Test
    @Description("Validando a funcionalidade setNumber and getNUmber do objeto AddressInput")
    public void testSetAndGetCep() {
        AddressInput address = new AddressInput();
        address.setCep("");
        assertEquals("", address.getCep());
    }

    @Test
    @Description("Validando a funcionalidade getNumber and setNumber do objeto AddressInput")
    public void testGetAndSetNumber() {
        AddressInput address = new AddressInput();
        address.setNumber("");
        assertEquals("", address.getNumber());
    }

}
