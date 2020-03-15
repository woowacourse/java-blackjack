package domain.result;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;

public class GameResult {

    private Map<Player, ResultType> resultOfPlayers;
    private Map<ResultType, Integer> resultOfDealer;
    private Map<User, Integer> userToCardPoint;

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(dealer, players);
    }

    private GameResult(Dealer dealer, Players players) {
        collectResult(dealer, players);
        createResultOfDealer();
        createUserToCardPoint(dealer, players);
    }

    private void collectResult(Dealer dealer, Players players) {
        resultOfPlayers = players.decideWinner(dealer);
    }

    private void createResultOfDealer() {
        Map<ResultType, Integer> resultOfDealer = new LinkedHashMap<>();

        Arrays.stream(ResultType.values())
                .forEach(result -> resultOfDealer.put(result, 0));

        resultOfPlayers.forEach(
                (player, result) -> resultOfDealer.computeIfPresent(result.getOppositeResult(),
                        (key, value) -> value + 1));

        this.resultOfDealer = Collections.unmodifiableMap(resultOfDealer);
    }

    private void createUserToCardPoint(Dealer dealer, Players players) {
        userToCardPoint = new LinkedHashMap<>();

        userToCardPoint.put(dealer, dealer.calculatePoint());
        players.getPlayers()
                .forEach(player -> userToCardPoint.put(player, player.calculatePoint()));
    }

    public Map<ResultType, Integer> getResultOfDealer() {
        return resultOfDealer;
    }

    public Map<Player, ResultType> getResultOfPlayers() {
        return resultOfPlayers;
    }

    public Map<User, Integer> getUserToCardPoint() {
        return userToCardPoint;
    }
}
