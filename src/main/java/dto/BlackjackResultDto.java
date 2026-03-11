package dto;

import java.util.List;

public record BlackjackResultDto(
    String name,
    List<String> hand,
    int score
) {

}
