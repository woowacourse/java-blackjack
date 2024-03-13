package view.gameresult;

import dto.GameResultDTO;
import java.util.List;
import view.Console;

public class GameResultOutputView {
    public static void print(GameResultDTO gameResultDTO) {
        List<String> playersName = gameResultDTO.getPlayersName();
        List<Integer> playersEarnMoney = gameResultDTO.getPlayersEarnMoney();
        Console.println("## 최종 수익");
        Integer sumOfPlayersEarnMoney = playersEarnMoney.stream().reduce(0, Integer::sum);
        Console.printf("딜러: %d%n", -sumOfPlayersEarnMoney);
        for (int index = 0; index < playersName.size(); index++) {
            Console.printf("%s: %d%n", playersName.get(index), playersEarnMoney.get(index));
        }
    }
}
