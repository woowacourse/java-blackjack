package controller.dto;

import java.util.List;

public record PlayerFaceUpResultInfo(
        String name,
        List<String> cardFaces,
        int hand
) {
}
