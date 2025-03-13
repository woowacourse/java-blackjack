package blackjack.domain;

import blackjack.common.Constants;
import java.util.List;

public class PlayerHand {

    private final Hand hand;
    private final Wallet wallet;

    public PlayerHand(Hand hand, Wallet wallet) {
        this.hand = hand;
        this.wallet = wallet;
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    public int getOptimisticValue() {
        return hand.getOptimisticValue();
    }

    public boolean canTakeCard() {
        return hand.canTakeCardWithin(Constants.BUSTED_STANDARD_VALUE);
    }

    public boolean isBlackjack() {
        return hand.isBlackJack();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public void adjustBalance(GameResultType gameResultType) {
        wallet.calculate(gameResultType);
    }

    public int getRevenue() {
        return wallet.getRevenue();
    }
}
