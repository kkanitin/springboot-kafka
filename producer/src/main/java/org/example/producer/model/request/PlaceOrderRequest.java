package org.example.producer.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class PlaceOrderRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -2067409769917150951L;
    @NotNull(message = "bookId is required")
    Integer bookId;
    @NotBlank(message = "username is required")
    String username;
}
