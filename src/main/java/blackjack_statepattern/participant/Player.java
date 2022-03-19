package blackjack_statepattern.participant;

import blackjack_statepattern.card.Cards;

public final class Player extends Participant {
    private final int betMoney;

    public Player(String name, int betMoney) {
        super(name);
        this.betMoney = betMoney;
    }

    public int getBetMoney() {
        return betMoney;
    }

    public double profit(Cards dealerCards) {
        return state.profit(dealerCards, betMoney);
    }

}
