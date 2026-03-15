package domain.participant;

import domain.game.Betting;
import domain.game.WinningStatus;
import domain.strategy.PlayerDrawStrategy;

public class Player extends Participant {
    private Betting betting;

    public Player(String name) {
        super(name, new PlayerDrawStrategy());
        this.betting = Betting.none();
    }

    public void bet(int amount) {
        betting = Betting.of(amount);
    }

    public int profit(WinningStatus status) {
        return betting.profit(status);
    }
}
