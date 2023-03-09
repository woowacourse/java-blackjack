package domain.card;

import java.util.List;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void receiveInitialCardsTest() {
        Cards cards = new Cards();
        List<Card> initialCards = List.of(new Card(Value.KING, Shape.SPADE), new Card(Value.QUEEN, Shape.HEART));
        cards.receiveInitialCards(initialCards);
    }
}
