package model.turn;

import java.util.List;
import model.PlayerChoice;
import model.card.Deck;
import view.InputView;
import view.OutputView;

public class PlayerTurns {
    private final List<PlayerTurn> playersGameRound;

    public PlayerTurns(List<PlayerTurn> playersGameRound) {
        this.playersGameRound = playersGameRound;
    }

    public void dealInitialCardsToAllPlayers(Deck deck) {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.dealInitialCards(deck);
        }
    }

    public void runPlayerTurn() {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.chooseAtOnePlayerTurn();
        }
    }

    public List<PlayerTurn> getPlayersGameRound() {
        return playersGameRound;
    }
}
