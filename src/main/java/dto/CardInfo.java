package dto;

import java.util.List;

public record CardInfo(
        String name,
        List<String> card,
        int score
) {
}
