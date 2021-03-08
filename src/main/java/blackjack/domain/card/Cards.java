package blackjack.domain.card;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public static Cards createAllShuffledCards() {
        Cards cards = new Cards();
        cards.createAllCards();
        cards.shuffle();
        return cards;
    }

    private void shuffle() {
        Collections.shuffle(cards);
    }

    private void createAllCards() {
        for (CardShapeType cardShape : CardShapeType.values()) {
            createCardsWithShape(cardShape);
        }
    }

    private void createCardsWithShape(CardShapeType cardShape) {
        for (CardNumberType cardNumber : CardNumberType.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public Card drawOneCard() {
        return cards.remove(cards.size() - 1);
    }
}
