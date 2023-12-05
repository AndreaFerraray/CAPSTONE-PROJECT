package CAPSTONE.PROJECT.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.ToString;
import org.springframework.stereotype.Component;


public record NewUserDTO(

        @NotEmpty(message = "l'username è un campo obbligatorio!")
        @Size(min = 3, max = 20, message = "Lo username deve essere compreso tra 3 e 20 caratteri")
        String username,

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "Il nome è un campo obbligatorio!")

        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String cognome,

        @NotEmpty  (message = "la password è obbligatoria")
        @Size(min=4, message="la password deve avere almeno 4 caratteri")
        String password








) {
        public NewUserDTO {
        }
}
