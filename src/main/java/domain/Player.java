package domain;

import domain.enums.MatchCase;

public class Player extends Participant {

    private final String name;
    private final Betting betting;

    public Player(String name, Betting betting) {
        this.name = name;
        this.betting = betting;
    }

    public void calculateMoney(MatchCase matchCase, boolean isDealerBlackjack) {
        betting.calculateMoney(matchCase, cards.isBlackjack(), isDealerBlackjack);
    }

    public String getName() {
        return name;
    }

    public int getBettingScore() {
        return betting.getBettingScore();
    }

}
