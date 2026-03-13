package controller.result;

import model.judgement.PlayerResult;
import model.judgement.Profit;
import view.OutputView;

public class ProfitReporter implements ResultReporter {

    @Override
    public void report(PlayerResult playerResult) {
        Profit dealerProfit = playerResult.calculateDealerProfit();

        OutputView.printFinalProfitHeader();
        OutputView.printProfitByDealer(dealerProfit);
        OutputView.printProfitByPlayers(playerResult.calculateProfits());
    }
}
