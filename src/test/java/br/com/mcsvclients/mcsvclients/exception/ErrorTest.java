package br.com.mcsvclients.mcsvclients.exception;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorTest {

    @Test
    @Description("Verificar o construtor, com parametro String, do objeto Error")
    public void testConstructor() {
        Error error = new Error("Error");
        assertEquals("Error", error.getMsg());
    }

    @Test
    @Description("Verificar o a funcionalidade getMsg e setMsg do objeto Error")
    public void testGetAndSetMsg() {
        Error error = new Error();
        error.setMsg("Error");
        assertEquals("Error", error.getMsg());
    }


}
