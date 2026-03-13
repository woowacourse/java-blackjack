package controller.result;

import model.judgement.DealerResult;
import model.judgement.PlayerResult;
import view.WinLossReportView;

public class NonBettingResultReporter implements ResultReporter {

    @Override
    public void report(PlayerResult playerResult) {
        DealerResult dealerResult = playerResult.calculateDealerResult();

        WinLossReportView.printFinalResultHeader();
        WinLossReportView.printResultByDealer(dealerResult);
        WinLossReportView.printResultByPlayers(playerResult);
    }
}
