package com.everisbootcamp.customers.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String message;
    private String status;
    private LocalDateTime timestamp = LocalDateTime.now(ZoneId.of("America/Lima"));

    public Response(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public Map<String, Object> getResponse() {
        Map<String, Object> response = new HashMap<>();

        response.put("status", this.getStatus());
        response.put("message", this.getMessage());
        response.put("timestamp", this.getTimestamp());

        return response;
    }
}
