package br.com.mcsvclients.mcsvclients.utils;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilTest extends Mockito {

    @Test
    @Description("Valida CEP retornando o valor do CEP")
    public void testValidateCEP() {
        Utils utils = new Utils();
        String cep = utils.validateCEP("65908295");
        assertEquals("65908295", cep);
    }

    @Test
    @Description("Valida CEP lançando um excessão caso o CEP vier errado")
    public void testValidateCEPThrowError() {
        Utils utils = new Utils();
        try {
            utils.validateCEP("7777777777777");
        } catch (ClassCastException e) {
            assertEquals("CEP inválido", e.getMessage());
        }
    }
}
