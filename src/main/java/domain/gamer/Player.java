package domain.gamer;

import domain.BettingMoney;
import domain.card.PlayingCards;
import domain.result.PlayerResult;

public class Player extends Gamer {
    private static final int INIT_BATTING_MONEY = 0;
    private final BettingMoney bettingMoney;

    public Player(PlayingCards playingCards, String name) {
        this(playingCards, name, INIT_BATTING_MONEY);
    }

    public Player(PlayingCards playingCards, String name, int bettingMoney) {
        super(playingCards, name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public int calculateEarning(PlayerResult playerResult) {
        return bettingMoney.calculateEarning(playerResult);
    }

    @Override
    public boolean isHittable() {
        return isNotBust();
    }
}
