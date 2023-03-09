package domain.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final Cards cards;
    private Score score;

    public Player(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
        this.score = makeScore(cards);
    }

    private Score makeScore(final Cards cards) {
        return Score.of(cards);
    }

    public static Player from(final String name) {
        return new Player(name, new Cards(new ArrayList<>()));
    }

    public void addCard(final Card card) {
        cards.add(card);
        score = makeScore(cards);
    }

    public boolean canReceiveCard() {
        return !score.isBust();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getCards());
    }

    public Score getScore() {
        return score;
    }
}
