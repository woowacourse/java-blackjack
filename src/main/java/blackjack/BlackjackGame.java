package blackjack;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.card.Card;
import blackjack.domain.entry.vo.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    public void start() {
        BlackjackTable blackjackTable = new BlackjackTable(InputView.inputNameAndBettingMoney());
        OutputView.printAllCards(blackjackTable.getAllPlayers());
        hitPlayers(blackjackTable);
        OutputView.printGameResult(blackjackTable.getGameResult());
    }

    private void hitPlayers(BlackjackTable blackjackTable) {
        for (Name name : blackjackTable.getPlayerNames()) {
            hit(blackjackTable, name);
        }
        OutputView.printDealerHitCount(blackjackTable.countHitDealer());
    }

    private void hit(BlackjackTable blackjackTable, Name name) {
        while (!blackjackTable.isFinishedPlayer(name)) {
            List<Card> cards = blackjackTable.hit(name, Command.find(InputView.inputCommand(name.getValue())));
            OutputView.printCard(name.getValue(), cards);
        }
    }
}
