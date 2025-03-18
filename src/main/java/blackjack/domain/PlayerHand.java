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

    public void takeCard(Card newCard) {
        hand.takeCard(newCard);
    }

    public boolean canHit() {
        return hand.canHitWithin(Constants.BUSTED_STANDARD_VALUE);
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public void adjustBalance(GameResultType gameResultType) {
        wallet.calculate(gameResultType, hand);
    }

    public int getBestCardValue() {
        return hand.getBestCardValue();
    }

    public List<Card> getAllCards() {
        return hand.getAllCards();
    }

    public int getRevenue() {
        return wallet.getRevenue();
    }
}
