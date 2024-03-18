package blackjack.domain.card;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.state.HandState;

public class HandPair {
    private final CardHand dealerHand;
    private final CardHand playerHand;

    private HandPair(CardHand dealerHand, CardHand playerHand) {
        this.dealerHand = dealerHand;
        this.playerHand = playerHand;
    }

    public static HandPair of(Dealer dealer, Player player) {
        return new HandPair(CardHand.from(dealer.getCardHand()), CardHand.from(player.getCardHand()));
    }

    public boolean isDealerOnly(HandState state) {
        return dealerHand.isState(state) && !playerHand.isState(state);
    }

    public boolean isPlayerOnly(HandState state) {
        return !dealerHand.isState(state) && playerHand.isState(state);
    }

    public boolean isBoth(HandState state) {
        return dealerHand.isState(state) && playerHand.isState(state);
    }

    public boolean isDealer(HandState state) {
        return dealerHand.isState(state);
    }

    public boolean isPlayer(HandState state) {
        return playerHand.isState(state);
    }

    public boolean isDealerHighScore() {
        return isBoth(HandState.NOT_BUST) && dealerHand.calculateScore() > playerHand.calculateScore();
    }

    public boolean isPlayerHighScore() {
        return isBoth(HandState.NOT_BUST) && playerHand.calculateScore() > dealerHand.calculateScore();
    }

    public boolean isSameScore() {
        return isBoth(HandState.NOT_BUST) && playerHand.calculateScore() == dealerHand.calculateScore();
    }
}
