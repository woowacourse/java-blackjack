package blackjack.domain.card;

public class Card {

    private final Emblem emblem;
    private final Grade grade;

    public Card(Emblem emblem, Grade grade) {
        this.emblem = emblem;
        this.grade = grade;
    }

    public boolean isAce() {
        return grade.isAce();
    }

    public String getCardName() {
        return grade.getName() + emblem.getName();
    }

    public int getCardValue() {
        return grade.getValue();
    }
}
