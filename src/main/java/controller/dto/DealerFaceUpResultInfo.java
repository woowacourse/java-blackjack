package controller.dto;

import java.util.List;

public record DealerFaceUpResultInfo(
        List<String> cardFaces,
        int hand
) {

}
