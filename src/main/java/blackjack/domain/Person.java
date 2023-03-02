package blackjack.domain;

import java.util.List;

public class Person {
    private final String name;
    private final Cards cards;

    public Person(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void addCard(Card card) {
        if (getScore() > 21) {
            throw new IllegalArgumentException("[ERROR] 점수가 21점을 넘으면 카드를 더 뽑을 수 없습니다.");
        }
        cards.addCard(card);
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
        if (score > 21) {
            return 0;
        }
        return score;
    }

    public boolean isPlayer() {
        return true;
    }

    public List<Card> getInitCards() {
        return getCards();
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
}
