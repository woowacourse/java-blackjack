package player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import card.Card;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;

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
            combat(dealer, player);
        }
    }

    private void combat(Dealer dealer, Player player) {
        boolean playerBust = player.isBust();
        int playerScore = player.calculateScore();
        boolean dealerBust = dealer.isBust();
        int dealerScore = dealer.calculateScore();
        if ((!playerBust && dealerBust) || (!dealerBust && isPlayerHigher(playerScore, dealerScore))) {
            player.win();
            dealer.lose();
        }
        if (playerBust || (isPlayerLower(playerScore, dealerScore) && !dealerBust)) {
            player.lose();
            dealer.win();
        }
        if (!playerBust && (playerScore == dealerScore)) {
            player.tie();
            dealer.tie();
        }
    }

    private boolean isPlayerLower(int playerScore, int dealerScore) {
        return playerScore < dealerScore;
    }

    private boolean isPlayerHigher(int playerScore, int dealerScore) {
        return playerScore > dealerScore;
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
