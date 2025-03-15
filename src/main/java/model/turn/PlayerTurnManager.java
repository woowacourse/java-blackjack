package model.turn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.betting.Betting;
import model.card.CardRank;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;

public class PlayerTurnManager {
    private final List<PlayerTurn> playersGameRound;

    public PlayerTurnManager(List<PlayerTurn> playersGameRound) {
        this.playersGameRound = playersGameRound;
    }

    public void dealInitialCardsToAllPlayers(Deck deck) {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.dealInitialCards(deck);
        }
    }

    public void runPlayerTurn(Deck deck) {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.selectAtOnePlayerChoice(deck);
        }
    }

    public void betInsurance(Dealer dealer) {
        if (dealer.isFirstCardAce()) {
            runPlayersBetInsurance();
        }
    }

    public Map<Player, Betting> getPlayersBet() {
        Map<Player, Betting> playersBet = new HashMap<>();
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.putBetting(playersBet);
        }
        return playersBet;
    }

    private void runPlayersBetInsurance() {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.betInsurance();
        }
    }
}
