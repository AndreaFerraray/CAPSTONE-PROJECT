package CAPSTONE.PROJECT.payload;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.TipoSistemazione;
import CAPSTONE.PROJECT.entities.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public record NewPrenotazioneDTO (
                                  @NotNull
                                  Long campeggioId ,

                                  LocalDate dataPrenotazione,
                                  @NotNull
                                  LocalDate data_check_in,
                                  @NotNull
                                  LocalDate data_check_out,
                                  @Min(value = 0, message = "Il numero minimo di ospiti è 1")
                                  @Max(value = 5, message = "Il numero massimo di ospiti è 4")
                                  Long ospiti,
                                  @NotNull
                                  Boolean cani,
                                  @NotNull
                                  Long costoTotale,
                                  @NotNull
                                  Long tipoSistemazioneId

                                  ){
public NewPrenotazioneDTO{}

}
