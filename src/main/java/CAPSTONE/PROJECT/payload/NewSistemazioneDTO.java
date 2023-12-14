package CAPSTONE.PROJECT.payload;

public record NewSistemazioneDTO(Long campeggio_id,
                                 String nomeSistemazione,
                                 Long personeAmmesse,
                                 Long prezzoNotte,
                                 Boolean areaCondizionata,
                                 Boolean frigo,
                                 Boolean bagnoPrivato,
                                 Boolean correnteElettrica,
                                 Boolean lavanderia
                                 ) {
}
