package controller.result;

import model.judgement.DealerResult;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import view.WinLossReportView;

public class WinLossReporter implements ResultReporter {

    @Override
    public void report(PlayerResult playerResult) {
        DealerResult dealerResult = Judgement.judgeByDealer(playerResult);

        WinLossReportView.printFinalResultHeader();
        WinLossReportView.printResultByDealer(dealerResult);
        WinLossReportView.printResultByPlayers(playerResult);
    }
}
