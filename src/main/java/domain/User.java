package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    private final List<Card> cards;

    public User(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    abstract boolean canHit();
    public abstract String getName();

    private int calculateScore() {
        List<Card> cardsExceptA = getCardsExceptA();
        int scoreExceptA = getScoreExceptA(cardsExceptA);
        int countA = cards.size() - cardsExceptA.size();
        return getFinalScore(countA, scoreExceptA);
    }

    private int getScoreExceptA(List<Card> cardsExceptA) {
        return cardsExceptA.stream()
                .map(Card::letter)
                .mapToInt(Letter::getScore)
                .sum();
    }

    private List<Card> getCardsExceptA() {
        return cards.stream()
                .filter(Card::isNotA)
                .collect(Collectors.toList());
    }

    private int getFinalScore(int countA, final int scoreExceptA) {
        int score = scoreExceptA;
        for (int i = 0; i < countA; i++) {
            score += Letter.getScoreFromA(score);
        }

        return score;
    }

    public Result compare(User other) {
        if (isDraw(other)) {
            return Result.DRAW;
        }
        if (isWon(other)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private boolean isWon(User other) {
        return !isBusted() && (getScore() > other.getScore() || other.isBusted());
    }

    private boolean isDraw(User other) {
        return getScore() == other.getScore() || (isBusted() && other.isBusted());
    }

    public int getScore() {
        return calculateScore();
    }

    abstract public boolean canHit();

    public boolean isBusted() {
        return getScore() > 21;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }
}


