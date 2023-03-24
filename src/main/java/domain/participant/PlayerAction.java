package domain.participant;

import java.util.Collection;

import domain.Result;
import domain.card.Card;
import domain.card.Deck;
import domain.hand.Hand;
import domain.hand.Score;

public class PlayerAction {

    private Hand hand;

    public PlayerAction(Collection<Card> cards) {
        this.hand = new Hand(cards);
    }

    public void drawFrom(Deck deck) {
        hand = hand.hit(deck.draw());
    }

    public Result competeWith(Player other) {
        if (hand.isDrawAgainst(other.getHand())) {
            return Result.DRAW;
        }
        if (hand.isWinnerAgainst(other.getHand())) {
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

    public Score getScore() {
        return hand.score();
    }

    public Hand getHand() {
        return hand;
    }
}
