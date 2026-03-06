package dto;

import java.util.List;

public record GamblerCardInfo(
        String name,
        List<String> card,
        int score
) {
}
