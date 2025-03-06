package blackjack.dto;

import blackjack.domain.Card;
import blackjack.domain.Nickname;
import java.util.List;

public record DrawnCardResult(
        Nickname nickname,
        List<Card> cards,
        int point
) {

}
