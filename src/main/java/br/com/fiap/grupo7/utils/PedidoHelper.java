package br.com.fiap.grupo7.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public abstract class PedidoHelper {

    public static Boolean checkIfListIsNullOrEmpty(List s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static String asJsonString(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}
