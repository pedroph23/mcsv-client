package br.com.mcsvclients.mcsvclients.model.output;

import lombok.Data;

@Data
public class DataResponse <T>{
    T data;

    public DataResponse(T data) {
        this.data = data;
    }
}
