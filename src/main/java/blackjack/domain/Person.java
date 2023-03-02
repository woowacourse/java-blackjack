package blackjack.domain;

import java.util.List;

public class Person {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MAX_SCORE = 21;

    private final String name;
    private final Cards cards;

    public Person(String name) {
        validate(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validate(String name) {
        validateBlankName(name);
        validateNameLength(name);
    }

    private void validateBlankName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백일 수 없습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
        }
    }

    public void addCard(Card card) {
        if (getScore() > MAX_SCORE) {
            throw new IllegalArgumentException("[ERROR] 점수가 " + MAX_SCORE + "점을 넘으면 카드를 더 뽑을 수 없습니다.");
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
        if (score > MAX_SCORE) {
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
