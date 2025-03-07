package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Nickname;

public record HiddenDealerHandState(
        Nickname nickname,
        Card card
) {

}
