package view.dto;

import domain.result.GameResult;
import domain.user.Player;
import domain.user.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultDto {

    private Map<UserDto, Integer> userToCardPoint;
    private Map<Player, Integer> profitOfPlayers;
    private int profitOfDealer;

    private GameResultDto(GameResult gameResult) {
        userToCardPoint = new LinkedHashMap<>();
        Map<User, Integer> userPoints = gameResult.getUserToCardPoint();
        for (User user : userPoints.keySet()) {
            userToCardPoint.put(UserDto.of(user), userPoints.get(user));
        }
        this.profitOfPlayers = gameResult.getProfitOfPlayers();
        this.profitOfDealer = gameResult.getProfitOfDealer();
    }

    public static GameResultDto of(GameResult gameResult) {
        return new GameResultDto(gameResult);
    }

    public Map<UserDto, Integer> getUserToCardPoint() {
        return userToCardPoint;
    }

    public Map<Player, Integer> getProfitOfPlayers() {
        return profitOfPlayers;
    }

    public int getProfitOfDealer() {
        return profitOfDealer;
    }
}
