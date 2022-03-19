package blackjack_statepattern.participant;

import blackjack_statepattern.card.Cards;

public final class Player extends Participant {
    private final BetMoney betMoney;

    public Player(String name, BetMoney betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    public double profit(Cards dealerCards) {
        return state.profit(dealerCards, betMoney.getAmount());
    }

}
