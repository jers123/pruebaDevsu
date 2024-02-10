package com.devsu.accountmovement.service;

import com.devsu.accountmovement.model.dto.ClienteDTO;
import com.devsu.accountmovement.utils.LocalDateTimeDeserializer;
import com.devsu.accountmovement.utils.ReplyMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.devsu.accountmovement.utils.SystemConstants.LOCAL_ORIGIN_CLIENTE_PATH;

@Component
public class Operations {

    @Autowired
    private RestTemplate restTemplate;

    public ReplyMessage getCliente(Integer id) {
        ReplyMessage replyMessage = null;
        try {
            replyMessage = restTemplate.getForObject(
                    LOCAL_ORIGIN_CLIENTE_PATH + id,
                    ReplyMessage.class
            );
        } catch (Exception e) {
            String json = e.getMessage().trim();
            if(json.matches(".* : \"\\{\"date\":\".*")) {
                json = json.substring(7, json.length()-1);
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()) // Registrar un adaptador para LocalDateTime
                        .create();
                replyMessage = gson.fromJson(json, ReplyMessage.class);

            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
        return replyMessage;
    }

    public ClienteDTO convertToClienteDTO(String object) {
        object = object.replace("{","");
        object = object.replace("}","");
        List<String> values = new ArrayList<>();
        String[] keyValuePairs = object.split(", ");
        String[] keyValue;
        for (String pair : keyValuePairs) {
            keyValue = pair.split("=");
            if (keyValue.length == 2) {
                values.add(keyValue[1].trim());
            }
        }
        ClienteDTO cliente = new ClienteDTO();
        cliente.setIdCliente(Integer.parseInt(values.get(0)));
        cliente.setNombre(values.get(1));
        cliente.setGenero(values.get(2));
        cliente.setEdad(Integer.parseInt(values.get(3)));
        cliente.setIdentificacion(values.get(4));
        cliente.setDireccion(values.get(5));
        cliente.setTelefono(Long.parseLong(values.get(6)));
        cliente.setContrasena(values.get(7));
        cliente.setEstado(Boolean.parseBoolean(values.get(8)));
        return cliente;
    }
}