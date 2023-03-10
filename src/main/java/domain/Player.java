package domain;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.card.Cards;

public class Player {

    private final String name;
    private final Cards cards;
    private Score score;

    public Player(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player from(final String name) {
        return new Player(name, Cards.empty());
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean canReceiveCard() {
        updateScore();
        return !score.isBust();
    }

    protected void updateScore() {
        score = Score.of(cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getCards());
    }

    public Score getScore() {
        updateScore();
        return score;
    }
}
