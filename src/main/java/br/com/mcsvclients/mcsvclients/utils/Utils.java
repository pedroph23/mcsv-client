package br.com.mcsvclients.mcsvclients.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public String validateCEP(String cep) {
        Pattern pattern = Pattern.compile("^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$");
        Matcher matcher = pattern.matcher(cep);
        if(matcher.find()) {
            return cep;
        }
        throw new ClassCastException("CEP inválido");
    }
}
