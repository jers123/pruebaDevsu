package com.devsu.clientperson.model.emtity;

import com.devsu.clientperson.model.entity.Cliente;
import com.devsu.clientperson.model.repository.IClienteRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@DataJpaTest
public class ClienteTest {

    @Autowired
    private IClienteRepository<Cliente> repository;

    private Cliente entity;

    Cliente init() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Edardo Martinez");
        cliente.setGenero("Masculino");
        cliente.setEdad(28);
        cliente.setIdentificacion("741258963");
        cliente.setDireccion("Zipaquira, Colombia");
        cliente.setTelefono(87654321L);
        cliente.setContrasena("pasword");
        cliente.setEstado(true);
        return cliente;
    }

    @Test
    void shouldSaveAndFindCliente() {
        entity = repository.save(init());
        assertNotNull(entity.getIdCliente());
        end();
    }

    @Test
    //@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldFindByNombre() {
        entity = repository.save(init());
        String nombre = repository.searchByName(entity.getIdCliente(), entity.getNombre());
        assertThat(nombre).contains(entity.getNombre());
        end();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldFindByIdentificacion() {
        entity = repository.save(init());
        String identificacion = repository.searchByIdentification(entity.getIdCliente(), entity.getIdentificacion());
        assertThat(identificacion).contains(entity.getIdentificacion());
        end();
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void shouldFindByTelefono() {
        entity = repository.save(init());
        Long telefono = repository.searchByTelephone(entity.getIdCliente(), entity.getTelefono());
        assertThat(telefono.toString()).contains(entity.getTelefono().toString());
        end();
    }

    void end() {
        repository.delete(entity);
    }
}