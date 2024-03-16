package blackjack.dto;

import blackjack.domain.card.Hands;
import blackjack.domain.user.UserName;

public record PlayerCardsDto(UserName name, Hands cards) {

    public String getName() {
        return name.getName();
    }
}
