package blackjack.domain.player;

import blackjack.domain.HitFlag;
import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Cards;
import blackjack.domain.strategy.HitStrategy;
import java.util.ArrayList;

public class Guest extends AbstractPlayer {

    private WinDrawLose winDrawLose;
    private final HitStrategy hitStrategy;

    public Guest(String name, HitStrategy hitStrategy) {
        super(name, new Cards(new ArrayList<>()));
        this.hitStrategy = hitStrategy;
    }

    @Override
    public Cards getShowCards() {
        return getCards();
    }

    @Override
    public void win() {
        winDrawLose = WinDrawLose.WIN;
    }

    @Override
    public void draw() {
        winDrawLose = WinDrawLose.DRAW;
    }

    @Override
    public void lose() {
        winDrawLose = WinDrawLose.LOSE;
    }

    @Override
    public String getWinDrawLoseString() {
        return winDrawLose.getName();
    }

    @Override
    public HitFlag checkHitFlag() {
        return hitStrategy.getHitFlag(this);
    }
}
