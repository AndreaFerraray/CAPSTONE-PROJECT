package CAPSTONE.PROJECT.payload;

import jakarta.mail.Multipart;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record NewPostDTO(
@NotNull
        String testo,
MultipartFile file


) {
}
