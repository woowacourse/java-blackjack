package blackjack.dto;

import blackjack.domain.card.Card;

public record HiddenDealerHandState(
        String nickname,
        Card card
) {

}
