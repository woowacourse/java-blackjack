package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static Cards instance;
    private final List<Card> cards = new ArrayList<>();

    public Cards() {
        createAllCards();
        Collections.shuffle(cards);
    }

    private void createAllCards() {
        for (CardShape cardShape : CardShape.values()) {
            createCardsWithShape(cardShape);
        }
    }

    private void createCardsWithShape(CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public static Cards getCards() {
        if (instance == null) {
            instance = new Cards();
        }
        return instance;
    }

    public Card drawOneCard() {
        return cards.remove(cards.size() - 1);
    }
}
