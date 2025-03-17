package deck;

import card.Card;
import card.CardNumber;
import card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckCreator implements DeckCreateStrategy {

    @Override
    public List<Card> createAllCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardShape, cardNumber));
            }
        }
        Collections.shuffle(cards);

        return cards;
    }
}
