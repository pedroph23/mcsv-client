package br.com.mcsvclients.mcsvclients.model.output;

import br.com.mcsvclients.mcsvclients.model.input.ClientInput;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Matchers.anyString;

public class ClientOutputTest {

    @Test
    @Description("Validando o construtor do objeto ClientOutput")
    public void testConstructor() {
        ClientOutput client = new ClientOutput();
        assertEquals(client, client);
    }

    @Test
    @Description("Validando o construtor com parametros do objeto ClientOutput")
    public void testConstructorWithParam() {
        ClientOutput client = new ClientOutput(anyString(), anyString(), anyList());
        assertEquals(client, client);
    }

    @Test
    @Description("Validando a funcionalidade getName e setName do objeto ClientOutput")
    public void testGetAndSetName() {
        ClientOutput client = new ClientOutput();
        client.setName(anyString());
        assertEquals(anyString(), client.getName());
    }


    @Test
    @Description("Validando a funcionalidade getCpf e setCpf do objeto ClientOutput")
    public void testGetAnSetCpf() {
        ClientOutput client = new ClientOutput();
        client.setCpf(anyString());
        assertEquals(anyString(), client.getCpf());
    }


    @Test
    @Description("Validando a funcionalidade setAddress e getAddress do objeto ClientOutput")
    public void testSetAndGetAddress() {
        ClientOutput client = new ClientOutput();
        client.setAddresses(anyList());
        assertEquals(anyList(), client.getAddresses());
    }
    
}
