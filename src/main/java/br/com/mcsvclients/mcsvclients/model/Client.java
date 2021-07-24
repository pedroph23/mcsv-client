package br.com.mcsvclients.mcsvclients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name ="tb_client", schema = "builders_db")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cpf")
    private String cpf;

    @JoinColumn (name ="fk_client")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    public Client(String name, String cpf, List<Address> addresses) {
        this.name = name;
        this.cpf = cpf;
        this.addresses = addresses;
    }

    public Client() {

    }
}
