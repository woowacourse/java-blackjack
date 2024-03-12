package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;

public record PlayerCardsDto(PlayerName name, Hands cards) {

    public String getName() {
        return name.getName();
    }
}
