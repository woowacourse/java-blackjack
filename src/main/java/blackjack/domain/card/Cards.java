package blackjack.domain.card;

import blackjack.domain.card.type.CardNumberType;
import blackjack.domain.card.type.CardShapeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cards {
    private static final int BLACKJACK_SIZE = 2;
    private static final int BLACKJACK_SCORE = 21;
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
        List<Card> cards = new ArrayList<>(this.cards);
        cards.sort(Comparator.comparing(Card::getValue).reversed());
        int scoreSum = 0;
        for (Card card : cards) {
            scoreSum += card.getBestValue(scoreSum);
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

    public boolean isBlackJack() {
        if (cards.size() == BLACKJACK_SIZE && getScore() == BLACKJACK_SCORE) {
            return true;
        }
        return false;
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }
}
