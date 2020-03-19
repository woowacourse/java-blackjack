package view.dto;

import java.util.Map;

import domain.user.Player;
import domain.user.User;

public class GameResultDto {

    private Map<User, Integer> userToCardPoint;
    private Map<Player, Integer> profitOfPlayers;
    private int profitOfDealer;

    public static GameResultDto of(Map<User, Integer> userToCardPoint, Map<Player, Integer> profitOfPlayers,
            int profitOfDealerGameResult) {
        return new GameResultDto(userToCardPoint, profitOfPlayers, profitOfDealerGameResult);
    }

    public GameResultDto(Map<User, Integer> userToCardPoint, Map<Player, Integer> profitOfPlayers,
            int profitOfDealer) {
        this.userToCardPoint = userToCardPoint;
        this.profitOfPlayers = profitOfPlayers;
        this.profitOfDealer = profitOfDealer;
    }

    public Map<User, Integer> getUserToCardPoint() {
        return userToCardPoint;
    }

    public Map<Player, Integer> getProfitOfPlayers() {
        return profitOfPlayers;
    }

    public int getProfitOfDealer() {
        return profitOfDealer;
    }
}
