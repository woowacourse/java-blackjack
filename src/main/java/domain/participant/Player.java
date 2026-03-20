package domain.participant;

import domain.game.Betting;
import domain.game.WinningStatus;

import static domain.game.BlackjackRule.BLACKJACK_SCORE;

public class Player extends Participant {
    private Betting betting;

    public Player(String name) {
        super(name);
        this.betting = Betting.none();
    }

    public void bet(int amount) {
        betting = Betting.of(amount);
    }

    public int profit(WinningStatus status) {
        return betting.profit(status);
    }

    @Override
    public boolean canDraw() {
        return score() < BLACKJACK_SCORE;
    }
}
