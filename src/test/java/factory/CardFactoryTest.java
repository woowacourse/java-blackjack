package factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.Test;

class CardFactoryTest {

    @Test
    void 덱이_정상적으로_생성되는_경우() {
        // given, when
        List<Card> cards = CardFactory.createDeck();

        // then
        assertFalse(cards.stream().distinct().count() != cards.size());
        assertEquals(52, cards.size());
    }
}
