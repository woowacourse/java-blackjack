package blackjack.domain.card;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    private Cards() {
    }

    public static Cards createAllShuffledCards() {
        Cards cards = new Cards();
        cards.createAllCards();
        cards.shuffle();
        return cards;
    }

    public static Cards createEmptyCards() {
        return new Cards();
    }

    public int getScore() {
        cards.sort((o1, o2) -> -Integer.compare(o1.getValue(), o2.getValue()));
        int scoreSum = 0;
        for (Card card : cards) {
            scoreSum += card.getValue(scoreSum);
        }
        return scoreSum;
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

    private void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawOneCard() {
        return cards.remove(cards.size() - 1);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
