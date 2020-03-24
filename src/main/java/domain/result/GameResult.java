package domain.result;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private Map<User, Integer> userToCardPoint;
    private Map<Player, Integer> profitOfPlayers;

    private GameResult(Dealer dealer, PlayersInfo playersInfo) {
        createUserToCardPoint(dealer, playersInfo);
        profitOfPlayers = playersInfo.calculateProfit(dealer);
    }

    public static GameResult of(Dealer dealer, PlayersInfo playersInfo) {
        return new GameResult(dealer, playersInfo);
    }

    private void createUserToCardPoint(Dealer dealer, PlayersInfo playersInfo) {
        userToCardPoint = new LinkedHashMap<>();

        userToCardPoint.put(dealer, dealer.calculatePoint());
        userToCardPoint.putAll(playersInfo.calculatePoint());
    }

    public int getProfitOfDealer() {
        int totalProfitOfPlayers = profitOfPlayers.values()
                .stream()
                .reduce(0, Integer::sum);

        return -totalProfitOfPlayers;
    }

    public Map<User, Integer> getUserToCardPoint() {
        return Collections.unmodifiableMap(userToCardPoint);
    }

    public Map<Player, Integer> getProfitOfPlayers() {
        return Collections.unmodifiableMap(profitOfPlayers);
    }
}
