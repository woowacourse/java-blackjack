package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> cards;

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    public int getScore() {
        int scoreSum = getScoreSumOf(cards);
        if (hasA(cards) && canAddTenTo(scoreSum)) {
            scoreSum = scoreSum + 10;
        }
        return scoreSum;
    }

    public Result competeWith(Player other) {
        if ((isBusted() && other.isBusted()) || getScore() == other.getScore()) {
            return Result.DRAW;
        }
        if (!isBusted() && (other.isBusted() || getScore() > other.getScore())) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    private int getScoreSumOf(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasA(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isA);
    }

    private boolean canAddTenTo(int score) {
        return score + 10 <= 21;
    }

    public boolean canHit() {
        return getScore() < 21;
    }

    public boolean isBusted() {
        return getScore() > 21;
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }
}


