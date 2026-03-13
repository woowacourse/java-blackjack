package view;

import java.util.Map;
import model.judgement.Profit;
import model.paticipant.Player;

public class ProfitReportView {

    private ProfitReportView() {}

    public static void printFinalProfitHeader() {
        System.out.println();
        System.out.println("## 최종 수익");
    }

    public static void printProfitByDealer(Profit profit) {
        System.out.printf("딜러: %d%n", profit.value());
    }

    public static void printProfitByPlayers(Map<Player, Profit> profits) {
        profits.forEach((player, profit) ->
                System.out.printf("%s: %d%n", player.getName(), profit.value())
        );
    }
}
