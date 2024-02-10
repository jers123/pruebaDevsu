package com.devsu.clientperson.config;

import com.devsu.clientperson.model.dto.ClienteDTO;
import com.devsu.clientperson.model.entity.Cliente;
import com.devsu.clientperson.utils.ReplyMessage;
import com.devsu.clientperson.utils.mapper.ClienteMapper;
import com.devsu.clientperson.utils.mapper.IMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ClientPersonConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    @Bean
    public ReplyMessage replyMessage() {
        return new ReplyMessage();
    }

    @Bean
    public IMapper<ClienteDTO, Cliente> clienteMapper() {
        return new ClienteMapper();
    }
}