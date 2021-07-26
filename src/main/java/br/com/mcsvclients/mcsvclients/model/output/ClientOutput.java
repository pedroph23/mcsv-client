package br.com.mcsvclients.mcsvclients.model.output;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data

public class ClientOutput implements Serializable {

    public Long id;

    public String name;

    public String cpf;

    public List<AddressOutput> addresses;

    public ClientOutput(Long id,String name, String cpf, List<AddressOutput> addresses) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.addresses = addresses;
    }

    public ClientOutput() {}
}
