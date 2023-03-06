package domain.game;

import domain.card.Cards;
import domain.user.Dealer;
import domain.user.DealerStatus;
import domain.user.Player;
import domain.user.PlayerStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final List<Player> players;
    private final Dealer dealer;
    private final Cards cards;

    public BlackJackGame(List<Player> players, Dealer dealer, Cards cards) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
        this.cards = cards;
    }

    public void drawOneMoreCardForPlayer(Player player) {
        player.receiveCard(cards.drawCard());
    }

    public void drawCardUntilOverSixteen() {
        while (dealer.isUserStatus(DealerStatus.UNDER_SEVENTEEN)) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public void judgeWinner() {
        for (Player player : players) {
            compareDealerWithPlayer(player);
        }
    }

    private void compareDealerWithPlayer(Player player) {
        if (player.isUserStatus(PlayerStatus.BUST)) {
            dealerWin(player);
            return;
        }
        if (player.isUserStatus(PlayerStatus.NORMAL) && dealer.isUserStatus(DealerStatus.BUST)) {
            playerWin(player);
            return;
        }
        compareScore(player);
    }

    private void playerWin(Player player) {
        dealer.lose();
        player.win();
    }

    private void dealerWin(Player player) {
        dealer.win();
        player.lose();
    }

    private void compareScore(Player player) {
        if (dealer.getScore() >= player.getScore()) {
            dealerWin(player);
        }
        playerWin(player);
    }

    public Map<String, Boolean> getUserFinalResult() {
        Map<String, Boolean> userFinalResult = new LinkedHashMap<>();

        for (Player player : players) {
            userFinalResult.put(player.getName(), player.isWinner());
        }

        return userFinalResult;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
