package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    public static final int BLACKJACK_SCORE = 21;
    private static final int ACE_PLUS_SCORE = 10;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Cards() {
        this(new ArrayList<>());
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .map(card -> card.rank().getValue())
                .reduce(0, Integer::sum);
        int aceCount = calculateAceCount();
        while (aceCount-- > 0 && !isBust(score + ACE_PLUS_SCORE)) {
            score += ACE_PLUS_SCORE;
        }
        return score;
    }

    private int calculateAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    protected GameStatus determineGameStatusByScore(final Cards other) {
        if (calculateScore() > other.calculateScore()) {
            return GameStatus.WIN;
        }
        if (calculateScore() < other.calculateScore()) {
            return GameStatus.LOSE;
        }
        return GameStatus.TIE;
    }

    public boolean isBust(int score) {
        return score > BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
