package br.com.mcsvclients.mcsvclients.service.impl;

import br.com.mcsvclients.mcsvclients.exception.ClientException;
import br.com.mcsvclients.mcsvclients.model.Address;
import br.com.mcsvclients.mcsvclients.model.Client;
import br.com.mcsvclients.mcsvclients.model.input.AddressInput;
import br.com.mcsvclients.mcsvclients.model.input.ClientInput;
import br.com.mcsvclients.mcsvclients.model.output.AddressOutput;
import br.com.mcsvclients.mcsvclients.model.output.ClientOutput;
import br.com.mcsvclients.mcsvclients.repository.ClientRepository;
import br.com.mcsvclients.mcsvclients.service.ClientService;
import br.com.mcsvclients.mcsvclients.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientOutput getById(Long id) throws ClientException{

        Optional<Client> client = this.clientRepository.findById(id);

       if(client.isPresent()) {
           List<AddressOutput> addressOutputs = new ArrayList<>();
           Client clientResult = client.get();

           for (Address add : clientResult.getAddresses()) {
               addressOutputs.add(new AddressOutput(add.getId(), add.getCity(), add.getStreet(), add.getNumber(), add.getCep()));
           }

           return new ClientOutput(clientResult.getName(), clientResult.getCpf(), addressOutputs);
       }

       throw new ClientException("Cliente com id ("+ id +"), não identificado");
    }

    @Override
    public void delete(Long id) throws ClientException {
//        this.getById(id);
        this.clientRepository.deleteById(id);
    }

    public List<ClientOutput> listAll() {
        List<ClientOutput> clientOutputs = new ArrayList<>();

        List<Client> clients = this.clientRepository.findAll();

        if(!CollectionUtils.isEmpty(clients)) {
            clients.forEach(client -> {
                List<AddressOutput> addressOutputs = new ArrayList<>();

                client.getAddresses().forEach(address -> {
                    addressOutputs.add(new AddressOutput(address.getId(), address.getCity(), address.getStreet(), address.getNumber(), address.getCep()));
                });

                clientOutputs.add(new ClientOutput(client.getName(), client.getCpf(), addressOutputs));
            });
            return clientOutputs;

        }
        return Collections.EMPTY_LIST;
    }

    public void create(ClientInput clientInput, Long id) throws ClientException {

        List<Address> addresses = this.convertInputToAddress(clientInput.addresses);
        Client client = new Client(clientInput.getName(), clientInput.getCpf(), addresses);

        if(id == null) {
            this.clientRepository.save(client);
        } else {
            this.clientRepository.findById(id).map(cl -> {
                cl.setId(id);
                cl.setName(clientInput.getName());
                cl.setCpf(clientInput.getCpf());
                cl.setAddresses(addresses);
                return this.clientRepository.save(cl);
            });
        }
    }

    public List<Address> convertInputToAddress (List<AddressInput> inputList) throws ClientException {

        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        Utils utils = new Utils();

        if(!inputList.isEmpty()) {
            inputList.stream().forEach(addressInput -> {
                address.setCity(addressInput.getCity());
                address.setStreet(addressInput.getStreet());
                address.setNumber(addressInput.getNumber());
                address.setCep(utils.validateCEP(addressInput.getCep()));
                addresses.add(address);
            });
            return addresses;
        }
        return Collections.EMPTY_LIST;
    }

}
