package blackjack.controller.dto.response;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.result.GamerProfitTable;
import blackjack.domain.result.Profit;

import java.util.Map;
import java.util.stream.Collectors;

public class GamersResultResponseDto {

    private final Map<String, Integer> nameMoneyTable;

    private GamersResultResponseDto(Map<String, Integer> nameMoneyTable) {
        this.nameMoneyTable = nameMoneyTable;
    }

    public static GamersResultResponseDto from(GamerProfitTable gamerProfitTable) {
        Map<Gamer, Profit> gamerProfits = gamerProfitTable.getGamerProfits();
        Map<String, Integer> nameProfits = gamerProfits.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> entry.getValue().getProfit()
                ));
        return new GamersResultResponseDto(nameProfits);
    }

    public Map<String, Integer> getNameMoneyTable() {
        return nameMoneyTable;
    }
}