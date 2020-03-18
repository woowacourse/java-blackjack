package domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;

public class GameResult {

    private Map<User, Integer> userToCardPoint;
    private Map<Player, Integer> profitOfPlayers;

    public static GameResult of(Dealer dealer, PlayersInfo playersInfo) {
        return new GameResult(dealer, playersInfo);
    }

    private GameResult(Dealer dealer, PlayersInfo playersInfo) {
        createUserToCardPoint(dealer, playersInfo);
        profitOfPlayers = playersInfo.calculateProfit(dealer);
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

        return totalProfitOfPlayers * (-1);
    }

    public Map<User, Integer> getUserToCardPoint() {
        return userToCardPoint;
    }

    public Map<Player, Integer> getProfitOfPlayers() {
        return profitOfPlayers;
    }
}
