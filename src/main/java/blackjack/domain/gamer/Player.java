package blackjack.domain.gamer;

import blackjack.domain.WinDrawLose;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer {

    private WinDrawLose winDrawLose;

    public Player(String name) {
        super(name, new Cards(new ArrayList<>()));
    }

    @Override
    public List<Card> getViewCard() {
        return getCards().getCards();
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
