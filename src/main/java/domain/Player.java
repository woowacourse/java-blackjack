package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private final String name;
    private final List<Card> cards;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    public int getScore() {
        int scoreSum = getScoreSumOf(cards);
        if (hasAIn(cards) && canAddTenTo(scoreSum)) {
            scoreSum = scoreSum + 10;
        }
        return scoreSum;
    }

    Result competeWith(Player other) {
        if ((isBusted() && other.isBusted()) || getScore() == other.getScore()) {
            return Result.DRAW;
        }
        if (!isBusted() && (other.isBusted() || getScore() > other.getScore())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    void addCard(Card card) {
        cards.add(card);
    }

    private int getScoreSumOf(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAIn(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    private boolean canAddTenTo(int score) {
        return score + 10 <= 21;
    }

    public abstract boolean canHit();

    boolean isBusted() {
        return getScore() > 21;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public String getName() {
        return name;
    }
}