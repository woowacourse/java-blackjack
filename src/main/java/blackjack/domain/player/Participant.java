package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.player.state.Blackjack;
import blackjack.domain.player.state.PlayerStatus;

import java.util.List;

public abstract class Participant {
    protected PlayerStatus playerStatus;

    protected Participant(List<Card> cards) {
        this.playerStatus = PlayerStatus.checkInitialBlackJack(new Hand(cards));
    }

    abstract boolean isAbleToReceive();

    public boolean isBlackJack() {
        return playerStatus.getClass() == Blackjack.class;
    }

    public void hit(Card card) {
        playerStatus = playerStatus.draw(card);
    }

    public void stay() {
        playerStatus = playerStatus.stay();
    }

    public Hand getHand() {
        return playerStatus.hand();
    }
}
