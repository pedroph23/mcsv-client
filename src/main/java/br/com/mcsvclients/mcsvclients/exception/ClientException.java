package br.com.mcsvclients.mcsvclients.exception;

public class ClientException extends Exception {
    public ClientException(String errorMessage) {
        super(errorMessage);
    }
}
