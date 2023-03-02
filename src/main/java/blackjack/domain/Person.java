package blackjack.domain;

public class Person {
    private final String name;
    private final Cards cards;

    public Person(String name) {
        this.name = name;
        this.cards = new Cards();
    }

    public void addCard(Card card) {
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

    public int getScore() {
        return cards.getScore();
    }
}
