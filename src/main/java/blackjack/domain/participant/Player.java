package blackjack.domain.participant;


import static blackjack.domain.card.Hand.BLACKJACK_SCORE;

import blackjack.domain.bet.BetAmount;
import blackjack.domain.result.GameOutcome;

public class Player extends User {

    private GameOutcome gameOutcome;
    private final BetAmount betAmount;

    public Player(String name, int betAmount) {
        super(name);
        this.gameOutcome = GameOutcome.DRAW;
        this.betAmount = new BetAmount(betAmount);
    }

    public BetAmount getBet() {
        return betAmount;
    }

    public GameOutcome getGameOutcome() {
        return gameOutcome;
    }

    public void mark(GameOutcome gameOutcome) {
        this.gameOutcome = gameOutcome;
    }

    public boolean canHit() {
        return getScore() < BLACKJACK_SCORE;
    }
}

