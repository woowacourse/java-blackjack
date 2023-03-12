package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

public class Player extends User {

    private BetAmount betAmount;

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public void updateCardScore(Card card) {
        handCards.updateCardScore(card);
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public void checkBlackjack(boolean isDealerBlackjack) {
        if (!isDealerBlackjack && isBlackjack()) {
            betAmount.setBlackjack();
        }
    }

    public void updateReceivingAmount(Result result) {
        betAmount.updateReceivingAmount(result);
    }

    public String getPlayerName() {
        return name.getName();
    }

    public double getRewards() {
        return betAmount.getReceivingAmount();
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }
}
