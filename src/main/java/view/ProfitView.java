package view;

import domain.money.Profit;
import domain.user.Playable;
import java.util.Map;

public class ProfitView {
    
    public static final String PROFIT_RESULT_MESSAGE = "## 최종 수익";
    public static final String PROFIT_MESSAGE = "%s: %d";
    
    public static void printProfitResult() {
        System.out.println(PROFIT_RESULT_MESSAGE);
    }
    
    public static void printGameProfit(Map<Playable, Profit> profitMap) {
        profitMap.forEach((player, profit) -> printProfit(player.getName(), profit.getProfit()));
    }
    
    public static void printProfit(String name, int profit) {
        System.out.printf(PROFIT_MESSAGE, name, profit);
        System.out.println();
    }
    
    public static void printDealerProfitResult(String dealerName, final Profit dealerProfit) {
        printProfit(dealerName, dealerProfit.getProfit());
    }
}
