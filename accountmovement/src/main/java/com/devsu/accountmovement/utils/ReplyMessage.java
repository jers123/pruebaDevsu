package com.devsu.accountmovement.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ReplyMessage {
    private LocalDateTime date;
    private HttpStatus httpStatus;
    private Boolean error;
    private List<String> message;
    private Object object;
}