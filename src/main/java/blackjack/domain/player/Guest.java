package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;

public class Guest extends AbstractPlayer implements Player {

    private static final int HIT_MAX_POINT = 21;

    private final BetMoney betMoney;

    public Guest(String name, PlayingCards playingCards, Integer money) {
        super(name, playingCards);
        this.betMoney = new BetMoney(money);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isWin(Player player) {
        if (!player.isDealer()) {
            return player.isWin(this);
        }
        if (player.isBust() && !this.isBust()) {
            return true;
        }
        return player.isLose(this) && !this.isBust();
    }

    @Override
    protected int limitHit() {
        return HIT_MAX_POINT;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}
