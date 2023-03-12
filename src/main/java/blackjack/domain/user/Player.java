package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.result.Result;

public class Player extends User {

    private BetAmount betAmount;

    public Player(Name name) {
        super(name);
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public String getPlayerName() {
        return name.getName();
    }

    public double getReceivingAmount() {
        return betAmount.getReceivingAmount();
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public void updateCardScore(Card card) {
        handCards.updateCardScore(card);
    }

    public void checkBlackjack(boolean isDealerBlackjack) {
        if (!isDealerBlackjack) {
            betAmount.setBlackjack();
        }
    }

    public void updateReceivingAmount(Result result) {
        betAmount.updateReceivingAmount(result);
    }
}
