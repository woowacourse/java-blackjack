package domain.player;

import domain.cards.Card;

import java.util.Collections;
import java.util.List;

public class Player {

    private static final int HIT_THRESHOLD = 21;

    private final PlayerName name;
    protected final Hand hand;

    public Player(String name) {
        this.name = new PlayerName(name);
        this.hand = new Hand();
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public boolean isBlackJackScore() {
        return hand.isBlackJack();
    }

    public boolean isBust() {
        return hand.calculateScore() > HIT_THRESHOLD;
    }

    public boolean canHit() {
        return hand.calculateScore() < HIT_THRESHOLD;
    }

    public int finalizeCardsScore() {
        return hand.calculateScore();
    }

    public String getPlayerName() {
        return name.getValue();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getCards());
    }
}
