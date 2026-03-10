package factory;

import domain.card.Card;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    @Test
    void 덱이_정상적으로_생성되는_경우() {
        // given, when
        List<Card> cards = CardFactory.createDeck();

        // then
        assertEquals(52, cards.size());
    }
}