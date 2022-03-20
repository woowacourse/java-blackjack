package blackjack.domain.player;

import blackjack.domain.card.PlayingCards;

public class Guest extends AbstractPlayer implements Player {

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
    public boolean isHit() {
        return playingCards.isHit();
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}
