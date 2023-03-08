package blackjack.domain;

import java.util.List;

public abstract class Person {
    protected static final int MAX_SCORE = 21;

    protected final String name;
    protected final Cards cards;

    protected Person(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public GameResult matchGame(Person otherPerson) {
        int otherScore = correctionOverScore(otherPerson.getScore());
        int myScore = correctionOverScore(this.getScore());
        if (otherScore > myScore) {
            return GameResult.LOSE;
        }
        if (otherScore < myScore) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    private int correctionOverScore(int score) {
        if (score > MAX_SCORE) {
            return 0;
        }
        return score;
    }

    public void addCard(Card card) {
        this.cards.addCard(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isPlayer();

    public abstract boolean isDealer();

    public abstract List<Card> getInitCards();

    public abstract boolean canDrawCard();
}
