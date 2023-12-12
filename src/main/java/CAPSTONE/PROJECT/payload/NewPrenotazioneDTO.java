package CAPSTONE.PROJECT.payload;

import CAPSTONE.PROJECT.entities.Campeggio;
import CAPSTONE.PROJECT.entities.User;
import lombok.Getter;

import java.util.Date;

public record NewPrenotazioneDTO (
                                  Long campeggioId ,

                                  Date dataPrenotazione,
                                  Date data_check_in,
                                  Date data_check_out,
                                  Long ospiti,
                                  Long notti
                                  ){
public NewPrenotazioneDTO{}


}
