package controller.result;

import model.judgement.DealerResult;
import model.judgement.Judgement;
import model.judgement.PlayerResult;
import view.OutputView;

public class WinLossReporter implements ResultReporter {

    @Override
    public void report(PlayerResult playerResult) {
        DealerResult dealerResult = Judgement.judgeByDealer(playerResult);

        OutputView.printFinalResultHeader();
        OutputView.printResultByDealer(dealerResult);
        OutputView.printResultByPlayers(playerResult);
    }
}
