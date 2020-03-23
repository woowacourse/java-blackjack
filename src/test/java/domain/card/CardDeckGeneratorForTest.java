package domain.card;

import java.util.Arrays;
import java.util.List;

public class CardDeckGeneratorForTest {
    public static CardDeck create(List<Card> cards) {
        return new CardDeck(cards);
    }

    public static CardDeck createByOneCard(Card card) {
        return new CardDeck(Arrays.asList(card));
    }
}
