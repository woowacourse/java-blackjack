package domain.gamer.dto;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.GameResults;

import java.util.ArrayList;
import java.util.List;

public class GamerMoneyDto {
    private String name;
    private int money;

    public GamerMoneyDto(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public static GamerMoneyDto of(Gamer gamer, int money) {
        return new GamerMoneyDto(gamer.getName(), money);
    }

    public static List<GamerMoneyDto> createDtos(Dealer dealer, Players players, GameResults gameResults) {
        List<GamerMoneyDto> gamerMoneyDtos = new ArrayList<>();
        gamerMoneyDtos.add(of(dealer, gameResults.calculateEarning(dealer)));
        for (Player player : players) {
            gamerMoneyDtos.add(of(player, gameResults.calculateEarning(player)));
        }
        return gamerMoneyDtos;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
