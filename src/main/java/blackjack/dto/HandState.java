package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;

public record HandState(
        String nickname,
        List<Card> cards,
        int point
) {

}
