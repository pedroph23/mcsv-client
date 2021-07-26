package br.com.mcsvclients.mcsvclients.model.input;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientInputTest extends Mockito {
    
    @Test
    @Description("Validando o construtor do objeto ClientInputTest")
    public void testConstructor() {
        ClientInput client = new ClientInput();
        assertEquals(client, client);
    }

    @Test
    @Description("Validando a funcionalidade getId e setId do objeto ClientInput")
    public void testGetAndSetId() {
        ClientInput client = new ClientInput();
        client.setId(anyLong());
        assertEquals(anyLong(), client.getId());
    }

    @Test
    @Description("Validando a funcionalidade getName e setName do objeto ClientInput")
    public void testGetAndSetName() {
        ClientInput client = new ClientInput();
        client.setName(anyString());
        assertEquals(anyString(), client.getName());
    }


    @Test
    @Description("Validando a funcionalidade getCpf e setCpf do objeto ClientInput")
    public void testGetAnSetCpf() {
        ClientInput client = new ClientInput();
        client.setCpf(anyString());
        assertEquals(anyString(), client.getCpf());
    }


    @Test
    @Description("Validando a funcionalidade setAddress e getAddress do objeto ClientInput")
    public void testSetAndGetAddress() {
        ClientInput client = new ClientInput();
        client.setAddresses(anyList());
        assertEquals(anyList(), client.getAddresses());
    }

}
