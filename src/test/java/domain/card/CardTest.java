package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void 카드를_생성한다() {
        CardEmblem emblem = CardEmblem.TWO;
        CardDenomination denomination = CardDenomination.DIAMOND;

        Card twoDiamond = Card.of(emblem, denomination);

        Assertions.assertEquals(
                twoDiamond,
                Card.of(CardEmblem.TWO, CardDenomination.DIAMOND)
        );
    }

}
