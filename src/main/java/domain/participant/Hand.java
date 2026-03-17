package domain.participant;

import domain.card.Card;
import domain.card.CardsSnapshot;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BUST_THRESHOLD = 21;
    private static final int ACE_ADJUST_SCORE = 10;
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public void addAll(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public int calculateScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.rank().getPoint();
        }

        for (Card card : cards) {
            score = calculateAceCase(card, score);
        }

        return score;
    }

    private int calculateAceCase(Card card, int score) {
        if (card.isAce()) {
            score = calculateAcePoint(score);
        }
        return score;
    }

    public CardsSnapshot snapshot() {
        return new CardsSnapshot(List.copyOf(cards));
    }

    public CardsSnapshot firstCardSnapshot() {
        return new CardsSnapshot(List.of(cards.getFirst()));
    }

    public Card firstCard() {
        return cards.getFirst();
    }

    private int calculateAcePoint(int currentScore) {
        if (currentScore > BUST_THRESHOLD) {
            return currentScore - ACE_ADJUST_SCORE;
        }
        return currentScore;
    }

    public List<Card> cards() {
        return List.copyOf(cards);
    }

    public int cardsCount() {
        return cards.size();
    }
}
