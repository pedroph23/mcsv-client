package br.com.mcsvclients.mcsvclients.controller;

import br.com.mcsvclients.mcsvclients.exception.ClientException;
import br.com.mcsvclients.mcsvclients.exception.Error;
import br.com.mcsvclients.mcsvclients.model.input.ClientInput;
import br.com.mcsvclients.mcsvclients.model.output.ClientOutput;
import br.com.mcsvclients.mcsvclients.model.output.DataResponse;
import br.com.mcsvclients.mcsvclients.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/v1/client")
public class ClientController {

    private static String INTERNAL_ERROR_MSG = "Erro interno. Por favor aguarde!";
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation("Realiza a listagem de clientes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ClientOutput.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    @GetMapping
    public ResponseEntity<DataResponse<List<ClientOutput>>> listAll() {
        try {
            return ResponseEntity.ok(new DataResponse(this.clientService.listAll()));
        } catch (HttpServerErrorException.InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResponse(new Error(INTERNAL_ERROR_MSG)));
        }
    }

    @ApiOperation("Realiza a busca de cliente por ID do cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = ClientOutput.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            ClientOutput output = this.clientService.getById(id);
            if(output != null) {
                return ResponseEntity.ok(new DataResponse(output));
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ClientException ce) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResponse(new Error(ce.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResponse(new Error(INTERNAL_ERROR_MSG)));
        }
    }

    @ApiOperation("Realiza a criação do cliente")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    @PostMapping
    public ResponseEntity create(@RequestBody ClientInput clientInput)  {
        try {
            this.clientService.create(clientInput, null);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ClassCastException ce) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResponse(new Error(ce.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResponse(new Error(INTERNAL_ERROR_MSG)));
        }
    }

    @ApiOperation("Realiza a remoção do cliente por ID do cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
       try {
           this.clientService.delete(id);
           return ResponseEntity.ok().build();
       } catch (EmptyResultDataAccessException erda) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResponse(new Error("Cliente com id ("+ id +"), não identificado")));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResponse(new Error(INTERNAL_ERROR_MSG)));
       }
    }

    @ApiOperation("Realiza a alteração de dados do cliente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error"  , response = Error.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody ClientInput clientInput, @PathVariable Long id) {
        try {
            this.clientService.create(clientInput, id);
            return ResponseEntity.ok().build();
        } catch (ClassCastException ce) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResponse(new Error(ce.getMessage())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DataResponse(new Error(INTERNAL_ERROR_MSG)));
        }
    }
}
