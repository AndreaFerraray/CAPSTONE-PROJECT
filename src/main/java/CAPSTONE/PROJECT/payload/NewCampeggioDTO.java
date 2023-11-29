package CAPSTONE.PROJECT.payload;




import jakarta.validation.constraints.*;

public record NewCampeggioDTO (



        Long postiDisp ,
        @NotEmpty(message = "indirizzo obbligatorio")
        String indirizzo ,
        @NotEmpty(message = "email obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotNull(message = "tel obbligatorio")
        Long numeroTelefono,

        boolean caniAmmessi,
        boolean piscina,

        boolean animazione,

        boolean market,

        boolean ristorante,


        @Min(value = 1, message = "Il numero minimo di stelle è 1")
        @Max(value = 5, message = "Il numero massimo di stelle è 5")
       Integer stelle,
        String logo,


        @NotEmpty(message = "la password è obbligatoria")
        String password

){ public  NewCampeggioDTO{}

}
