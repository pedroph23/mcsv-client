package br.com.mcsvclients.mcsvclients.repository;

import br.com.mcsvclients.mcsvclients.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    <S extends Client> S save(S s);

}
