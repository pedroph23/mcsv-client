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

    @NonNull
    @Column(name = "city")
    private String city;

    @NonNull
    @Column(name = "street")
    private String street;

    @NonNull
    @Column(name = "number")
    private String number;

    @NonNull
    @Column(name = "cep")
    private String cep;

    public Address() {

    }
}
