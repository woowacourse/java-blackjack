package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.user.UserName;

public record PlayerHandsScoreDto(UserName name, Hands hands) {

    public int handsScore() {
        return hands.calculateScore().toInt();
    }

    public String getName() {
        return name.getName();
    }
}
