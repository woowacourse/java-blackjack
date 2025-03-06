package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final int BONUS_THRESHOLD = 11;
    private static final int ACE_BONUS = 10;

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Card hitCard() {
        return cards.removeFirst();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sum() {
        int sum = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        if(sum <= BONUS_THRESHOLD && hasA())sum+= ACE_BONUS;
        return sum;
    }

    private boolean hasA() {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    public List<Card> getCards() {
        return cards;
    }
}
