package br.com.mcsvclients.mcsvclients.model.output;

import br.com.mcsvclients.mcsvclients.model.Address;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataResponseTest extends Mockito {

    @Test
    @Description("Validando a funcionalidade construtor do objeto DataResponse")
    public void testConstructor() {
        DataResponse dataResponse = new DataResponse();
        assertEquals(dataResponse, dataResponse);
    }

    @Test
    @Description("Validando a funcionalidade construtor com parametros do objeto DataResponse")
    public void testConstructorWithParam() {
        DataResponse dataResponse = new DataResponse(Object.class);
        assertEquals(Object.class, dataResponse.getData());
    }

    @Test
    @Description("Validando a funcionalidade getData and setData do objeto DataResponse")
    public void testGetAndSetCity() {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(Object.class);
        assertEquals(Object.class, dataResponse.getData());
    }
}
