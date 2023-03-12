package blackjack.domain.player;

import blackjack.domain.card.Card;
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

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Hand getHand() {
        return hand;
    }

}
