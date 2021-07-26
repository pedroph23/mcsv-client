package br.com.mcsvclients.mcsvclients.model;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest extends Mockito {

    @Test
    @Description("Validando o construtor do objeto AddressTest")
    public void testConstructor() {
        Address address = new Address();
        assertEquals(address, address);
    }

    @Test
    @Description("Validando a funcionalidade getId  do objeto Address")
    public void testGetId() {
        Address address = mock(Address.class);
        when(address.getId()).thenReturn(1l);
        assertEquals(1l, address.getId());
    }

    @Test
    @Description("Validando a funcionalidade getCity and setCity do objeto Address")
    public void testGetAndSetCity() {
        Address address = new Address();
        address.setCity(anyString());
        assertEquals(anyString(), address.getCity());
    }

    @Test
    @Description("Validando a funcionalidade getSreet and setStreet do objeto Address")
    public void testGetAndSetStreet() {
        Address address = new Address();
        address.setStreet(anyString());
        assertEquals(anyString(), address.getStreet());
    }

    @Test
    @Description("Validando a funcionalidade getCep and setCep do objeto Address")
    public void testGetAndSetCep() {
        Address address = new Address();
        address.setCep(anyString());
        assertEquals(anyString(), address.getCep());
    }

    @Test
    @Description("Validando a funcionalidade getNUmber and setNumber do objeto Address")
    public void testGetAndSetNumber() {
        Address address = new Address();
        address.setNumber(anyString());
        assertEquals(anyString(), address.getNumber());
    }

    @Test
    @Description("Validando a funcionalidade setId do objeto Address")
    public void testSetId() {
        Address address = new Address();
        address.setId(1l);
        assertEquals(1l, address.getId());
    }


}
