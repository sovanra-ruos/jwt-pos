package co.itc.pos.features.file;

import co.itc.pos.features.file.dto.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request);

    List<String> uploadMultipleFiles(MultipartFile[] files, HttpServletRequest request);

}
