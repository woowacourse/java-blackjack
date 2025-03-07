package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Nickname;
import java.util.List;

public record HandState(
        Nickname nickname,
        List<Card> cards,
        int point
) {

}
