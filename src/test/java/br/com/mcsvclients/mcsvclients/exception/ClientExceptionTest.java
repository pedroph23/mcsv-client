package br.com.mcsvclients.mcsvclients.exception;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientExceptionTest extends Mockito {

    @Test
    @Description("Validar o construtor do objeto CLientException")
    public void testClientException() {
        ClientException exception = new ClientException("Teste de excessão");
        assertEquals("Teste de excessão", exception.getMessage());
    }

}
