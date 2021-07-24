package br.com.mcsvclients.mcsvclients.service;

import br.com.mcsvclients.mcsvclients.exception.ClientException;
import br.com.mcsvclients.mcsvclients.model.input.ClientInput;
import br.com.mcsvclients.mcsvclients.model.output.ClientOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientOutput> listAll();

    void create(ClientInput clientInput, Long id) throws ClientException;

    ClientOutput getById(Long id) throws ClientException;

    void delete(Long id) throws ClientException;
}
