package br.com.mcsvclients.mcsvclients.model.output;

import lombok.Data;

@Data
public class AddressOutput {

    public Long id;

    public String city;

    public String street;

    public String number;

    public String cep;

    public AddressOutput(Long id, String city, String street, String number, String cep) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.cep = cep;
    }

    public AddressOutput() {}
}
