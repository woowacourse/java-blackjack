package blackjack.model;

import blackjack.util.PlayerParser;
import java.util.ArrayList;
import java.util.List;

public class Users {

    private final List<Player> players;
    private final Dealer dealer;

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

    public void determineWinner() {
        for (Player player : players) {
            if (calculateWhenBlackjack(player, dealer)) {
                continue;
            }

            if (calculateWhenBust(player, dealer)) {
                continue;
            }

            calculateWhenNormal(player, dealer);
        }
    }

    private boolean calculateWhenBlackjack(Player player, Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            dealer.addResult(GameResult.DRAW);
            return true;
        }

        if (player.isBlackjack()) {
            player.mark(GameResult.WIN);
            dealer.addResult(GameResult.LOSE);
            return true;
        }

        if (dealer.isBlackjack()) {
            player.mark(GameResult.LOSE);
            dealer.addResult(GameResult.WIN);
            return true;
        }

        return false;
    }

    private boolean calculateWhenBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            dealer.addResult(GameResult.WIN);
            player.mark(GameResult.LOSE);
            return true;
        }

        if (dealer.isBust()) {
            dealer.addResult(GameResult.LOSE);
            player.mark(GameResult.WIN);
            return true;
        }

        return false;
    }

    private static void calculateWhenNormal(Player player, Dealer dealer) {
        if (player.totalScore() > dealer.totalScore()) {
            dealer.addResult(GameResult.LOSE);
            player.mark(GameResult.WIN);
            return;
        }

        if (player.totalScore() < dealer.totalScore()) {
            dealer.addResult(GameResult.WIN);
            player.mark(GameResult.LOSE);
            return;
        }

        dealer.addResult(GameResult.DRAW);
    }
}
