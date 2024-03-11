package domain.gamer;

import domain.cards.Card;

import java.util.Collections;
import java.util.List;

public class Player {

    private static final int HIT_THRESHOLD = 21;

    private final GamerName name;
    protected final Hand cards;

    public Player(String name, Hand cards) {
        this.name = new GamerName(name);
        this.cards = cards;
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public boolean isBust() {
        return cards.calculateScore() > HIT_THRESHOLD;
    }

    public boolean canHit() {
        return cards.calculateScore() < HIT_THRESHOLD;
    }

    public int finalizeCardsScore() {
        return cards.calculateScore();
    }

    public String getPlayerName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
