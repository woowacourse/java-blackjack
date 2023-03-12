package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.player.state.Blackjack;
import blackjack.domain.player.state.PlayerStatus;

import java.util.List;

public abstract class Participant {
    protected final Hand hand;
    protected PlayerStatus playerStatus;

    protected Participant(List<Card> cards) {
        this.hand = new Hand(cards);
        this.playerStatus = PlayerStatus.checkInitialBlackJack(hand);
    }

    abstract boolean isAbleToReceive();

    public boolean isBlackJack() {
        return playerStatus.getClass() == Blackjack.class;
    }

    public void hit(Card card) {
        playerStatus = playerStatus.draw(card);
    }

    public Hand getHand() {
        return hand;
    }
}
