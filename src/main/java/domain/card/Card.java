package domain.card;

public class Card {

    private final Emblem emblem;
    private final Grade grade;

    public Card(Emblem emblem, Grade grade) {
        this.emblem = emblem;
        this.grade = grade;
    }

    public int getCardValue() {
        return grade.getValue();
    }

    public boolean isAce() {
        return grade.isAce();
    }

}
