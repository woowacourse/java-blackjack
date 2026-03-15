package controller.result;

import model.judgement.DealerResult;
import model.judgement.PlayerResult;
import model.paticipant.Player;
import view.WinLossReportView;

public class NonBettingResultReporter implements ResultReporter<Player> {

    @Override
    public void report(PlayerResult<Player> playerResult) {
        DealerResult dealerResult = playerResult.calculateDealerResult();

        WinLossReportView.printFinalResultHeader();
        WinLossReportView.printResultByDealer(dealerResult);
        WinLossReportView.printResultByPlayers(playerResult);
    }
}
