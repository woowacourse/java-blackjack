package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    private final String name;
    private Hand hand;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    void addCard(Card card) {
        hand.hit(card);
    }

    public void drawFrom(Deck deck) {
        Card drawnCard = deck.draw();
        hand = hand.hit(drawnCard);
    }

    public int getScore() {
        return hand.score();
    }

    Result competeWith(Player other) {
        if (hand.isDrawAgainst(other.hand)) {
            return Result.DRAW;
        }
        if (hand.isWinnerAgainst(other.hand)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    public abstract boolean canHit();

    public Hand hand() {
        return hand;
    }

    public List<Card> getCards() {
        return new ArrayList<>(hand.cards());
    }

    public String getName() {
        return name;
    }
}