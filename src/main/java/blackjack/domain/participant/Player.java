package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Player extends Participant {

    private final BetMoney betMoney;
    private boolean isStay = false;

    public Player(Name name, Cards cards, BetMoney betMoney) {
        super(name, cards);
        this.betMoney = betMoney;
    }

    public Player(Name name, Cards cards) {
        this(name, cards, new BetMoney(BetMoney.UNIT));
    }

    public void stay() {
        isStay = true;
    }

    @Override
    public boolean isFinished() {
        return cards.isBust() || cards.isBlackJack() || isStay;
    }

    public boolean isStay() {
        return isStay;
    }

    public double calculateRevenue(double leverage) {
        return betMoney.calculateRevenue(leverage);
    }
}
