package co.itc.pos.features.file;

import co.itc.pos.features.file.dto.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<FileResponse> uploadSingleFile(
            @RequestPart("file") MultipartFile file, HttpServletRequest request
    ) {
        return ResponseEntity.ok(fileService.uploadSingleFile(file, request));
    }


    @PostMapping(value = "/multiple", consumes = "multipart/form-data")
    public ResponseEntity<List<String>> uploadMultipleFiles(
            @RequestPart("files") MultipartFile[] files, HttpServletRequest request
    ) {
        return ResponseEntity.ok(fileService.uploadMultipleFiles(files, request));
    }
}
