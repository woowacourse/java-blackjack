package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int EMPTY_SCORE = 0;
    private static final int BONUS_SCORE = 10;
    private static final int LIMIT_SCORE = 21;

    private List<Card> cards;

    public Cards() {
        this(new ArrayList<>());
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public int getScore() {
        int score = 0;

        if (isContainAce(cards)) {
            score = calculateContainAce();
        }

        if (score != EMPTY_SCORE && score <= LIMIT_SCORE) {
            return score;
        }

        return calculate();
    }

    private int calculate() {
        return cards.stream()
                .mapToInt(Card::value)
                .sum();
    }

    private int calculateContainAce() {
        return calculate() + BONUS_SCORE;
    }

    private static boolean isContainAce(List<Card> cards) {
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
