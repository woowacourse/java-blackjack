package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.Map;

public class GameBoard {
    public static final int INITIAL_CARD_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    public GameBoard(Players players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public int countPlayers() {
        return players.size();
    }

    public boolean isPlayerNotOver(int playerIndex) {
        return players.isOnePlayerNotOver(playerIndex);
    }

    public boolean isDealerNotOver() {
        return dealer.isNotOver(Dealer.DEALER_BOUNDARY_SCORE);
    }

    public void distributeInitialHand() {
        dealer.setInitialHand();
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            distributeOnePlayerInitialHand(playerIndex);
        }
    }

    private void distributeOnePlayerInitialHand(int playerIndex) {
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            players.receiveOnePlayerCard(dealer.drawCard(), playerIndex);
        }
    }

    public void addCardToPlayer(int playerIndex) {
        players.receiveOnePlayerCard(dealer.drawCard(), playerIndex);
    }

    public void addCardToDealer() {
        dealer.receiveCard(dealer.drawCard());
    }

    public Map<Player, Boolean> calculateVictory() {
        Map<Player, Boolean> victory = players.calculateVictory(dealer.calculateScore());
        calculateBettingMoney(victory);
        return victory;
    }

    private void calculateBettingMoney(Map<Player, Boolean> victory) {
        for(Player onePlayer: victory.keySet()) {
            calculateOnePlayerBettingMoney(victory.get(onePlayer), onePlayer);
        }
    }

    private void calculateOnePlayerBettingMoney(Boolean victory, Player onePlayer) {
        if (victory) {
            dealer.loseMoney(onePlayer.getMoney());
            onePlayer.earnBetSuccessMoney();
            return;
        }
        dealer.gainMoney(onePlayer.getMoney());
        onePlayer.payBetFailMoney();
    }

    public Name getPlayerName(int playerIndex) {
        return players.getOnePlayerName(playerIndex);
    }

    public Players getPlayers() {
        return players;
    }

    public Player getPlayer(int playerIndex) {
        return players.getOnePlayer(playerIndex);
    }

    public Dealer getDealer() {
        return dealer;
    }
}
