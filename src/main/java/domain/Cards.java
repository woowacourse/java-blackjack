package domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    void add(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int scoreSum = getScoreSumOf(cards);
        if (hasAIn(cards) && canAddTenTo(scoreSum)) {
            scoreSum = scoreSum + 10;
        }
        return scoreSum;
    }

    Result competeWith(Cards other) {
        if ((isBusted() && other.isBusted()) || getScore() == other.getScore()) {
            return Result.DRAW;
        }
        if (!isBusted() && (other.isBusted() || getScore() > other.getScore())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private boolean hasAIn(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    private int getScoreSumOf(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean canAddTenTo(int score) {
        return score + 10 <= 21;
    }

    boolean isBusted() {
        return getScore() > 21;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
