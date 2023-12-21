package CAPSTONE.PROJECT.payload;

import org.springframework.web.multipart.MultipartFile;

public record NewFotoCampeggioDTO(
        Long idCampeggio,
        MultipartFile foto
) {
}
