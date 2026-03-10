package blackjack.model.user;

import blackjack.model.GameResult;
import blackjack.model.PlayersGameResult;
import blackjack.util.PlayerParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

    public Users(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Users(String playerNames) {
        this.players = PlayerParser.parse(playerNames);
        this.dealer = new Dealer();
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(List.copyOf(players));
        users.add(dealer);
        return users;
    }

    public PlayersGameResult determineWinner() {
        Map<Player, GameResult> result = new HashMap<>();
        for (Player player : players) {
            if (calculateWhenBlackjack(player, dealer, result)) {
                continue;
            }

            if (calculateWhenBust(player, dealer, result)) {
                continue;
            }

            calculateWhenNormal(player, dealer, result);
        }
        return new PlayersGameResult(result);
    }

    private boolean calculateWhenBlackjack(Player player, Dealer dealer, Map<Player, GameResult> result) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            logDraw(player, dealer, result);
            return true;
        }

        if (player.isBlackjack()) {
            logPlayerWin(player, dealer, result);
            return true;
        }

        if (dealer.isBlackjack()) {
            logPlayerLose(player, dealer, result);
            return true;
        }

        return false;
    }

    private boolean calculateWhenBust(Player player, Dealer dealer, Map<Player, GameResult> result) {
        if (player.isBust()) {
            logPlayerLose(player, dealer, result);
            return true;
        }

        if (dealer.isBust()) {
            logPlayerWin(player, dealer, result);
            return true;
        }

        return false;
    }

    private void calculateWhenNormal(Player player, Dealer dealer, Map<Player, GameResult> result) {
        if (player.totalScore() > dealer.totalScore()) {
            logPlayerWin(player, dealer, result);
            return;
        }

        if (player.totalScore() < dealer.totalScore()) {
            logPlayerLose(player, dealer, result);
            return;
        }

        logDraw(player, dealer, result);
    }

    private void logPlayerWin(Player player, Dealer dealer, Map<Player, GameResult> result) {
        result.put(player, GameResult.WIN);
        dealer.addResult(GameResult.LOSE);
    }

    private void logPlayerLose(Player player, Dealer dealer, Map<Player, GameResult> result) {
        result.put(player, GameResult.LOSE);
        dealer.addResult(GameResult.WIN);
    }

    private void logDraw(Player player, Dealer dealer, Map<Player, GameResult> result) {
        result.put(player, GameResult.DRAW);
        dealer.addResult(GameResult.DRAW);
    }
}
