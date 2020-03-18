package domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;
import view.dto.UserDto;

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

    public Map<UserDto, Integer> getUserDtoToCardPoint() {
        Map<UserDto, Integer> userDtoToCardPoint = new LinkedHashMap<>();
        for (User user : userToCardPoint.keySet()) {
            userDtoToCardPoint.put(UserDto.of(user.getName(), user.getCards()),
                    userToCardPoint.get(user));
        }
        return userDtoToCardPoint;
    }

    public Map<Player, Integer> getProfitOfPlayers() {
        return profitOfPlayers;
    }
}
