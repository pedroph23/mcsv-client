package br.com.mcsvclients.mcsvclients.exception;

import lombok.Data;

@Data
public class Error {
    public String msg;

    public Error(String msg) {
        this.msg = msg;
    }

    public Error() { }
}
