package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;

public abstract class Finished implements GameStatus {

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    abstract public GameStatus play(Dealer dealer, Players players, Deck deck);
}
