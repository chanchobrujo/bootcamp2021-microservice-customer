package com.everisbootcamp.customers.Model;

import com.everisbootcamp.customers.Constant.enums.MessagesError;
import com.everisbootcamp.customers.Constant.enums.MessagesSuccess;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class Response {

    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("America/Lima"));

    public Response(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public Response(MessagesSuccess message) {
        this.message = message.getMessages();
        this.status = HttpStatus.valueOf(message.getCod());
    }

    public Response(MessagesError message) {
        this.message = message.getMessages();
        this.status = HttpStatus.valueOf(message.getCod());
    }

    public Map<String, Object> getResponse() {
        Map<String, Object> response = new HashMap<>();

        response.put("message", this.getMessage());
        response.put("timestamp", this.getTimestamp());

        return response;
    }
}
