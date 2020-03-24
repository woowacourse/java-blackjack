package domain.gamer.dto;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.result.GameResults;

import java.util.ArrayList;
import java.util.List;

public class GamerMoneyAssembler {
    private GamerMoneyAssembler() {
    }

    public static GamerMoneyDto create(Gamer gamer, int money) {
        return new GamerMoneyDto(gamer.getName(), money);
    }

    public static List<GamerMoneyDto> createDtos(Dealer dealer, Players players, GameResults gameResults) {
        List<GamerMoneyDto> gamerMoneyDtos = new ArrayList<>();
        gamerMoneyDtos.add(create(dealer, gameResults.calculateEarning(dealer)));
        for (Player player : players) {
            gamerMoneyDtos.add(create(player, gameResults.calculateEarning(player)));
        }
        return gamerMoneyDtos;
    }
}
