package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final int BUST_NUMBER = 21;
    private static final int ACE_WEIGHT = 10;

    private final DrawStrategy drawStrategy;
    private final List<Card> cards;

    public Hand(DrawStrategy drawStrategy, List<Card> cards) {
        this.drawStrategy = drawStrategy;
        this.cards = new ArrayList<>(cards);
    }

    void drawCard() {
        cards.add(createCard());
    }

    List<String> cardInfos() {
        return cards.stream().map(Card::info).collect(Collectors.toList());
    }

    List<Card> cards() {
        return List.copyOf(cards);
    }

    boolean isBusted() {
        int score = rawScoreSum();
        if (aceCount() > 0) {
            score -= aceCount() * ACE_WEIGHT;
        }
        return score > BUST_NUMBER;
    }

    int scoreSum() {
        int total = rawScoreSum();
        if (isOvercome()) {
            total -= aceCount() * ACE_WEIGHT;
        }
        return total;
    }

    private int rawScoreSum() {
        return cards.stream().mapToInt(Card::score).sum();
    }

    private int aceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private Card createCard() {
        return drawStrategy.draw();
    }

    private boolean isOvercome() {
        return rawScoreSum() > BUST_NUMBER;
    }
}
