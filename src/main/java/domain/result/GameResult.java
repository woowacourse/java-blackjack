package domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;

public class GameResult {

    private Map<User, Integer> userToCardPoint;
    private Map<Player, Double> profitOfPlayers;

    public static GameResult of(Dealer dealer, PlayersInfo playersInfo) {
        return new GameResult(dealer, playersInfo);
    }

    private GameResult(Dealer dealer, PlayersInfo playersInfo) {
        profitOfPlayers = playersInfo.calculateProfit(dealer);
        createUserToCardPoint(dealer, playersInfo);
    }

    private void createUserToCardPoint(Dealer dealer, PlayersInfo playersInfo) {
        userToCardPoint = new LinkedHashMap<>();

        userToCardPoint.put(dealer, dealer.calculatePoint());
        playersInfo.getPlayers()
                .forEach(player -> userToCardPoint.put(player, player.calculatePoint()));
    }

    public double getProfitOfDealer() {
        double totalProfitOfPlayers = profitOfPlayers.values()
                .stream()
                .reduce(0D, Double::sum);

        return totalProfitOfPlayers * (-1);
    }

    public Map<User, Integer> getUserToCardPoint() {
        return userToCardPoint;
    }

    public Map<Player, Double> getProfitOfPlayers() {
        return profitOfPlayers;
    }
}
