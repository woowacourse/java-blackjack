package blackjack.domain.card;


import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int BUST_SIZE = 21;
    private static final int CHANGE_A_VALUE = 10;

    private List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int sum() {
        return cards.stream()
                    .mapToInt(Card::getCardScore)
                    .sum();
    }

    public boolean containAce() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getFirstCard() {
        return List.of(cards.get(0));
    }

    public List<Card> toList() {
        return cards;
    }

    public int calculateScore() {
        int sum = sum();
        if (containAce() && sum + CHANGE_A_VALUE <= BUST_SIZE) {
            return sum + CHANGE_A_VALUE;
        }
        return sum;
    }

    public boolean isBust() {
        return sum() > BUST_SIZE;
    }
}
