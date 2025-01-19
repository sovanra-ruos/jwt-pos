package co.itc.pos.features.file.dto;

import lombok.Builder;

@Builder
public record FileResponse(
        String fileName,
        String fullUrl,
        String downloadUrl,
        String fileType,
        float size
) {
}
