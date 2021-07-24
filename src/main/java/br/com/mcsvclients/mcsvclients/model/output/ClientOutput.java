package br.com.mcsvclients.mcsvclients.model.output;

import br.com.mcsvclients.mcsvclients.model.Address;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class ClientOutput implements Serializable {

    public String name;

    public String cpf;

    public List<AddressOutput> addresses;

    public ClientOutput(String name, String cpf, List<AddressOutput> addresses) {
        this.name = name;
        this.cpf = cpf;
        this.addresses = addresses;
    }
}
