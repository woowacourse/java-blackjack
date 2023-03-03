package player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import card.Card;

public class Players {
    private final List<Player> players = new ArrayList<>();

    private static void combat(Dealer dealer, Player player) {
        boolean playerBust = player.isBust();
        int playerScore = player.calculateScore();
        boolean dealerBust = dealer.isBust();
        int dealerScore = dealer.calculateScore();
        if (!playerBust && dealerBust || isPlayerHigher(playerScore, dealerScore)) {
            player.win();
            dealer.lose();
        }
        if (!dealerBust && playerBust || isPlayerLower(playerScore, dealerScore)) {
            player.lose();
            dealer.win();
        }
        if ((playerScore == dealerScore) && !playerBust) {
            player.tie();
            dealer.tie();
        }
    }

    private static boolean isPlayerLower(int playerScore, int dealerScore) {
        return playerScore < dealerScore;
    }

    private static boolean isPlayerHigher(int playerScore, int dealerScore) {
        return playerScore > dealerScore;
    }

    public void add(Player player) {
        players.add(player);
    }

    public void takeCard(int playerIndex, Card card) {
        Player player = players.get(playerIndex);
        player.hit(card);
    }

    public int count() {
        return players.size();
    }

    public boolean isBust(int playerIndex) {
        return players.get(playerIndex).isBust();
    }

    public List<PlayerResultDto> getPlayerResults() {
        return players.stream()
                .map(PlayerResultDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public void calculateWinning(Dealer dealer) {
        for (Player player : players) {
            combat(dealer, player);
        }
    }

    public List<PlayerWinningDto> getWinningResults() {
        return players.stream()
                .map(PlayerWinningDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<PlayerOpenDto> getPlayerCards() {
        return players.stream()
                .map(PlayerOpenDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public Name findPlayer(int playerIndex) {
        return players.get(playerIndex).getName();
    }
}
