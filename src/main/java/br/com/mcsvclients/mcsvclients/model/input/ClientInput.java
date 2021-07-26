package br.com.mcsvclients.mcsvclients.model.input;

import br.com.mcsvclients.mcsvclients.model.Address;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
public class ClientInput {

    public Long id;

    public String name;

    @CPF
    public String cpf;

    public List<AddressInput> addresses;

    public ClientInput(Long id, String name, String cpf, List<AddressInput> addresses) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.addresses = addresses;
    }

    public ClientInput() { }
}
