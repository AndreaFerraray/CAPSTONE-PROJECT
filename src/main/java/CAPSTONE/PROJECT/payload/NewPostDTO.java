package CAPSTONE.PROJECT.payload;

import jakarta.mail.Multipart;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record NewPostDTO(
@NotNull
        String testo,
LocalDate dataPubblicazione,
MultipartFile file


) {
}
