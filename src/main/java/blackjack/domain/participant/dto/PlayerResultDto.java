package blackjack.domain.participant.dto;

import blackjack.domain.card.Card;
import java.util.List;

public record PlayerResultDto(PlayerDto playerDto, int score) {
    public String getName() {
        return playerDto.name();
    }

    public List<Card> getCards() {
        return playerDto.cards();
    }
}
