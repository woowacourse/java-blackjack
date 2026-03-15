package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void 카드를_생성한다() {
        // given
        CardDenomination emblem = CardDenomination.TWO;
        CardEmblem denomination = CardEmblem.DIAMOND;

        // when
        Card twoDiamond = Card.of(emblem, denomination);

        // then
        Assertions.assertEquals(
                twoDiamond,
                Card.of(CardDenomination.TWO, CardEmblem.DIAMOND)
        );
    }

}
