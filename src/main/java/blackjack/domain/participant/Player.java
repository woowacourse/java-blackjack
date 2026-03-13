package blackjack.domain.participant;


import static blackjack.domain.card.Hand.BLACKJACK_SCORE;

import blackjack.domain.bet.BetAmount;

public class Player extends User {

    private final BetAmount betAmount;

    public Player(String name, int betAmount) {
        super(name);
        this.betAmount = new BetAmount(betAmount);
    }

    public BetAmount getBet() {
        return betAmount;
    }

    public boolean canHit() {
        return getScore() < BLACKJACK_SCORE;
    }
}

