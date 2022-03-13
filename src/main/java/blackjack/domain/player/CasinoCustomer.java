package blackjack.domain.player;

import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public class CasinoCustomer extends AbstractPlayer {

    private WinDrawLose winDrawLose;

    public CasinoCustomer(String name) {
        super(name, new Cards(new ArrayList<>()));
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
}
