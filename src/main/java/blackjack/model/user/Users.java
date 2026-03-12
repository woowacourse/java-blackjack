package blackjack.model.user;

import blackjack.model.gameresult.GameResult;
import blackjack.model.gameresult.PlayersGameResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Users {

    private static final String ERROR_DEALER_NOT_FOUND = "딜러가 존재하지 않습니다.";

    private final List<User> users;

    public Users(List<Player> players, Dealer dealer) {
        users = new ArrayList<>(players);
        users.add(dealer);
    }

    public Users(String playerNames) {
        users = new ArrayList<>(PlayerParser.parse(playerNames));
        users.add(new Dealer());
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(Player.class::isInstance)
                .map(Player.class::cast)
                .toList();
    }

    public Dealer getDealer() {
        return users.stream()
                .filter(Dealer.class::isInstance)
                .map(Dealer.class::cast)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_DEALER_NOT_FOUND));
    }

    public List<User> getUsers() {
        return List.copyOf(users);
    }

    public PlayersGameResult determineWinner() {
        Map<Player, GameResult> result = new HashMap<>();
        List<Player> players = getPlayers();
        Dealer dealer = getDealer();

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
