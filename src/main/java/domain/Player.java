package domain;

import java.util.List;

public class Player extends Participant {
    private final BetMoney betMoney;

    public Player(Name name, BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    @Override
    public List<Card> getInitialVisibleCards() {
        return super.getCards();
    }

    @Override
    public boolean isDrawable() {
        return super.isLessThanMaxScore();
    }

    public Long calculateBettingProfit(double returnRate) {
        return (long) (betMoney.betAmount() * returnRate);
    }
}
