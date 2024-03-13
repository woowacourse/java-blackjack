package domain.gamer;

import domain.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void initCard(Dealer dealer) {
        for (Player player : players) {
            player.receiveCard(dealer.dealCard());
            player.receiveCard(dealer.dealCard());
        }
    }

    public void play(PlayerTurn playerTurn, Dealer dealer) {
        for (Player player : players) {
            playerTurn.playTurn(player, dealer);
        }
    }

    public List<Name> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public GameResult getResult(int dealerScore) {
        Map<String, Boolean> result = new LinkedHashMap<>();
        for (Player player : players) {
            String playerName = player.getName().getValue();
            Boolean winOrLose = player.getMaxGameScore() > dealerScore;
            result.put(playerName, winOrLose);
        }
        return new GameResult(result);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
