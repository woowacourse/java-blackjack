package view.gameresult;

import domain.blackjack.EarningMoney;
import dto.GameResultDTO;
import java.util.List;
import view.Console;

public class GameResultOutputView {
    public static void print(GameResultDTO gameResultDTO) {
        List<String> playersName = gameResultDTO.getPlayersName();
        List<EarningMoney> playersEarnMoney = gameResultDTO.getPlayersEarnMoney();
        Console.println("## 최종 수익");
        EarningMoney dealerEarningMoney = gameResultDTO.getDealerEarningMoney();
        Console.printf("딜러: %d%n", dealerEarningMoney.rawEarningMoney());
        for (int index = 0; index < playersName.size(); index++) {
            Console.printf("%s: %d%n", playersName.get(index), playersEarnMoney.get(index).rawEarningMoney());
        }
    }
}
