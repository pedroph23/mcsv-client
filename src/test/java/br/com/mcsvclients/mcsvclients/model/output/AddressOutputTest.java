package br.com.mcsvclients.mcsvclients.model.output;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressOutputTest extends Mockito {
    @Test
    @Description("Validando o construtor com parametros do objeto AddressOutput")
    public void testConstructorWithParamsS() {
        AddressOutput address = new AddressOutput(anyLong(), anyString(), anyString(), anyString(), anyString());
        assertEquals(address, address);
    }

    @Test
    @Description("Validando o construtor do objeto AddressOutput")
    public void testConstructor() {
        AddressOutput address = new AddressOutput();
        assertEquals(address, address);
    }

    @Test
    @Description("Validando a funcionalidade setId e GetId do objeto AddressOutput")
    public void testSetAndGetId() {
        AddressOutput address = new AddressOutput();
        address.setId(anyLong());
        assertEquals(anyLong(), address.getId());
    }

    @Test
    @Description("Validando a funcionalidade getCity e SetId do objeto AddressOutput")
    public void testGetAndSetCity() {
        AddressOutput address = new AddressOutput();
        address.setCity("");
        assertEquals("", address.getCity());
    }

    @Test
    @Description("Validando a funcionalidade getSreet e setStreet do objeto AddressOutput")
    public void testGetAndSetStreet() {
        AddressOutput address = new AddressOutput();
        address.setStreet("");
        assertEquals("", address.getStreet());
    }

    @Test
    @Description("Validando a funcionalidade getCep e setCep do objeto AddressOutput")
    public void testGetAndSetCep() {
        AddressOutput address = new AddressOutput();
        address.setCep("");
        assertEquals("", address.getCep());
    }

    @Test
    @Description("Validando a funcionalidade getNumber e setNumber do objeto AddressOutput")
    public void testGetAndSetNumber() {
        AddressOutput address = new AddressOutput();
        address.setNumber("");
        assertEquals("", address.getNumber());
    }
}
