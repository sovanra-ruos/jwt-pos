package co.itc.pos.features.file;

import co.itc.pos.features.file.dto.FileResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    @Value("${file_storage.image_location}")
    String fileStorageDir;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final Set<String> SUPPORTED_IMAGE_TYPES = Set.of(
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE);

    private String generateImageUrl(HttpServletRequest request, String filename) {
        return String.format("%s://%s:%d/images/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                filename);
    }

    private String generateDownloadImageUrl(HttpServletRequest request, String filename) {
        return String.format("%s://%s:%d/api/v1/files/download/%s",
                request.getScheme(),
                request.getServerName(),
                request.getServerPort(),
                filename);
    }

    private String uploadFile(MultipartFile file) {

        logger.info("File name: {}", file.getOriginalFilename());

        String contentType = file.getContentType();
        if (!SUPPORTED_IMAGE_TYPES.contains(contentType)) {
            throw new ResponseStatusException(
                    HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                    contentType + " not allowed!!");
        }
        try {
            logger.info("File Storage Dir: {}", fileStorageDir);
            Path fileStoragePath = Path.of(fileStorageDir);
            try {
                if (!Files.exists(fileStoragePath)) {
                    Files.createDirectories(fileStoragePath);
                    logger.info("Created directory: {}", fileStoragePath);
                } else {
                    logger.info("Directory already exists: {}", fileStoragePath);
                }
            } catch (IOException e) {
                logger.error("Failed to create directory: {}", fileStoragePath, e);
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create directory", e);
            }

            String fileName = Objects.requireNonNull(file.getOriginalFilename());
            Files.copy(file.getInputStream(),
                    fileStoragePath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public FileResponse uploadSingleFile(MultipartFile file, HttpServletRequest request) {
        String filename = uploadFile(file);
        String fullImageUrl = generateImageUrl(request, filename);
        return FileResponse.builder()
                .downloadUrl(generateDownloadImageUrl(request,filename))
                .fileType(file.getContentType())
                .size((float) file.getSize() / 1024) // in KB
                .fileName(filename)
                .fullUrl(fullImageUrl).build();
    }

    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] files, HttpServletRequest request) {
        var fileUrls = new ArrayList<String>();
        for (var file : files) {
            FileResponse fileResponse = uploadSingleFile(file, request);
            fileUrls.add(fileResponse.fullUrl());
        }
        return fileUrls;
    }


}
