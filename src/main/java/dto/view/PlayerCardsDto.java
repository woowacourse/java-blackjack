package dto.view;

import java.util.List;

public record PlayerCardsDto(
        String name,
        List<String> cards
) {
}
