package factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Cards;
import org.junit.jupiter.api.Test;

class CardFactoryTest {

    @Test
    void 덱이_정상적으로_생성되는_경우() {
        // given, when
        Cards cards = CardFactory.createDeck();

        // then
        assertEquals(cards.cards().stream().distinct().count(), cards.size());
        assertEquals(52, cards.size());
    }
}
