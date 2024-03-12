package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.player.PlayerName;

public record PlayerHandsScoreDto(PlayerName name, Hands hands) {

    public int handsScore() {
        return hands.calculateScore().toInt();
    }

    public String getName() {
        return name.getName();
    }
}
