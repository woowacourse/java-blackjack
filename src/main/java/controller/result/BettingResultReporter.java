package controller.result;

import model.judgement.PlayerResult;
import model.judgement.Profit;
import view.ProfitReportView;

public class BettingResultReporter implements ResultReporter {

    @Override
    public void report(PlayerResult playerResult) {
        Profit dealerProfit = playerResult.calculateDealerProfit();

        ProfitReportView.printFinalProfitHeader();
        ProfitReportView.printProfitByDealer(dealerProfit);
        ProfitReportView.printProfitByPlayers(playerResult.calculateProfits());
    }
}
