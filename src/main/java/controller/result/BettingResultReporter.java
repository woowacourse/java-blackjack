package controller.result;

import java.util.Map;
import model.judgement.BettingCalculator;
import model.judgement.PlayerResult;
import model.judgement.Profit;
import model.paticipant.BettingPlayer;
import view.ProfitReportView;

public class BettingResultReporter implements ResultReporter {

    private final BettingCalculator calculator = new BettingCalculator();

    @Override
    public void report(PlayerResult playerResult) {
        Map<BettingPlayer, Profit> profits = calculator.calculateProfits(playerResult);
        Profit dealerProfit = calculator.calculateDealerProfit(profits);

        ProfitReportView.printFinalProfitHeader();
        ProfitReportView.printProfitByDealer(dealerProfit);
        ProfitReportView.printProfitByPlayers(profits);
    }
}