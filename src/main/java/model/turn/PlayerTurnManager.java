package model.turn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Betting;
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

    public Map<Player, Betting> getPlayersBet() {
        Map<Player, Betting> playersBet = new HashMap<>();
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.putBetting(playersBet);
        }
        return playersBet;
    }

    public void betInsurance(Dealer dealer) {
        if (dealer.openFirstCard().getCardRank() == CardRank.ACE) {
            runPlayersBetInsurance();
        }
    }

    private void runPlayersBetInsurance() {
        for (PlayerTurn playerTurn : playersGameRound) {
            playerTurn.betInsurance();
        }
    }
}
