package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;

public class TerminateGame implements GameStatus {

    @Override
    public boolean isPlayable() {
        return false;
    }

    @Override
    public GameStatus play(Dealer dealer, Players players, Deck deck) {
        return new TerminateGame();
    }
}
