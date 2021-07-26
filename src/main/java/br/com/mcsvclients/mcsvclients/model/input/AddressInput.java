package br.com.mcsvclients.mcsvclients.model.input;

import lombok.Data;

@Data
public class AddressInput {


    public String city;

    public String street;

    public String number;

    public String cep;

    public AddressInput(String city, String street, String number, String cep) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.cep = cep;
    }

    public AddressInput() { }
}
