package br.com.mcsvclients.mcsvclients.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_address", schema = "builders_db")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "cep")
    private String cep;

    public Address() {

    }

    public Address(Long id, @NonNull String city, @NonNull String street, @NonNull String number, @NonNull String cep) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.number = number;
        this.cep = cep;
    }
}
