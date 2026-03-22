package dto.view;

import java.util.List;

public record StartBlackJackDto(
        String dealerCard,
        List<PlayerCardsDto> playerCards
) {
}
