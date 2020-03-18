package view.dto;

import java.util.Map;

import domain.user.Player;

public class GameResultDto {

    private Map<UserDto, Integer> userDtoToCardPoint;
    private Map<Player, Integer> profitOfPlayers;
    private int profitOfDealer;

    public static GameResultDto of(Map<UserDto, Integer> userDtoToCardPoint, Map<Player, Integer> profitOfPlayers,
            int profitOfDealerGameResult) {
        return new GameResultDto(userDtoToCardPoint, profitOfPlayers, profitOfDealerGameResult);
    }

    public GameResultDto(Map<UserDto, Integer> userDtoToCardPoint, Map<Player, Integer> profitOfPlayers,
            int profitOfDealer) {
        this.userDtoToCardPoint = userDtoToCardPoint;
        this.profitOfPlayers = profitOfPlayers;
        this.profitOfDealer = profitOfDealer;
    }

    public Map<UserDto, Integer> getUserDtoToCardPoint() {
        return userDtoToCardPoint;
    }

    public Map<Player, Integer> getProfitOfPlayers() {
        return profitOfPlayers;
    }

    public int getProfitOfDealer() {
        return profitOfDealer;
    }
}
