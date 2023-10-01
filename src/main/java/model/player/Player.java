package model.player;

import model.card.Card;
import model.cards.Cards;
import model.name.Name;

import java.util.List;

import static util.Rule.GOAL_SCORE;

public class Player {

    private final Name name;
    private final Cards cards;
    private int grade;

    private Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
        this.grade = Integer.MIN_VALUE;
    }

    public static Player of(final Name name, final Cards cards) {
        return new Player(name, cards);
    }

    public void addCards(List<Card> card) {
        cards.addCards(card);
    }

    public void writeGrade(int grade) {
        this.grade = grade;
    }

    public void downScoreIfScoreExceedAndHaveAce() {
        if (getScore() > GOAL_SCORE.getValue()) {
            cards.changeScoreIfHaveAce();
        }
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public int getDistance(int goal) {
        return Math.abs(grade - goal);
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getGrade() {
        return grade;
    }
}
