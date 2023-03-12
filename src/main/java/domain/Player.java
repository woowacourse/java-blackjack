package domain;

import java.util.List;

public abstract class Player {

    private final String name;
    private Hand hand;

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    public void drawFrom(Deck deck) {
        Card drawnCard = deck.draw();
        hand = hand.hit(drawnCard);
    }

    public Score getScore() {
        return hand.score();
    }

    Result competeWith(Player other) {
        if (hand.isDrawAgainst(other.hand)) {
            return Result.DRAW;
        }
        if (hand.isWinnerAgainst(other.hand)) {
            return winConsideringBlackjack();
        }
        return Result.LOSE;
    }

    private Result winConsideringBlackjack() {
        if (hand.isBlackjack()) {
            return Result.WIN_BY_BLACKJACK;
        }
        return Result.WIN;
    }

    public abstract boolean canHit();

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}