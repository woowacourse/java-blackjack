package model.turn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.betting.Betting;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import view.InputView;

public class PlayersTurn {
    private final List<PlayerTurn> playersGameRound;

    public PlayersTurn(List<PlayerTurn> playersGameRound) {
        this.playersGameRound = playersGameRound;
    }

    public void dealInitialCardsToAllPlayers(Deck deck) {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.dealInitialCards(deck);
        }
    }

    public Map<Player, Betting> getPlayersBet() {
        Map<Player, Betting> playersBet = new HashMap<>();
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.putBetting(playersBet);
        }
        return playersBet;
    }

    public List<PlayerTurn> getPlayersGameRound() {
        return playersGameRound;
    }
}
