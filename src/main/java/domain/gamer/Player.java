package domain.gamer;

import domain.BattingMoney;
import domain.card.PlayingCards;
import domain.result.PlayerResult;

public class Player extends Gamer {
    private static final int INIT_BATTING_MONEY = 0;
    private final BattingMoney battingMoney;

    public Player(PlayingCards playingCards, String name) {
        this(playingCards, name, INIT_BATTING_MONEY);
    }

    public Player(PlayingCards playingCards, String name, int battingMoney) {
        super(playingCards, name);
        this.battingMoney = new BattingMoney(battingMoney);
    }

    public int calculateEarning(PlayerResult playerResult) {
        return battingMoney.calculateEarning(playerResult);
    }

    public int getBattingMoney() {
        return battingMoney.getBattingMoney();
    }

    @Override
    public boolean isHittable() {
        return isNotBust();
    }
}
