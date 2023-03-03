package player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import card.Card;

public class Players {
    private final List<Player> players = new ArrayList<>();

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
            boolean playerBust = player.isBust();
            int playerScore = player.calculateScore();
            boolean dealerBust = dealer.isBust();
            int dealerScore = dealer.calculateScore();
            if ((playerScore > dealerScore && !playerBust) || (dealerBust && !playerBust)) {
                player.win();
                dealer.lose();
            }
            if (playerBust || playerScore < dealerScore && !dealerBust) {
                player.lose();
                dealer.win();
            }
            if (playerScore == dealerScore) {
                player.tie();
                dealer.tie();
            }
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
}
