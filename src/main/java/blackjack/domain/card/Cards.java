package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int getScore() {
        int score = 0;

        if (containAceCard(cards)) {
            score = calculate(10);
        }

        if (score != 0 && score <= 21) {
            return score;
        }

        return calculate();
    }

    private int calculate() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    private int calculate(int bonusScore) {
        return cards.stream()
                .mapToInt(Card::value)
                .sum() + bonusScore;
    }

    private static boolean containAceCard(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public List<Card> getCards() {
        return cards;
    }
}
