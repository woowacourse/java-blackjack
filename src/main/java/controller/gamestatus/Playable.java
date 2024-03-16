package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;

public abstract class Playable implements GameStatus {

    @Override
    public boolean isPlayable() {
        return true;
    }
    
    abstract public GameStatus play(Dealer dealer, Players players, Deck deck);
}
