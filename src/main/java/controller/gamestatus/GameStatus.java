package controller.gamestatus;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Players;

public interface GameStatus {
    
    boolean isPlayable();

    GameStatus play(Dealer dealer, Players players, Deck deck);
}
