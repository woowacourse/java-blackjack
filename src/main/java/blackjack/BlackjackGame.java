package blackjack;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.vo.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    public void start() {
        // 카드 분배
        BlackjackTable blackjackTable = new BlackjackTable(InputView.inputNameAndBettingMoney());
        OutputView.printAllCards(blackjackTable.getDealer(), blackjackTable.getPlayers());

        // HIT
        for (Name name : blackjackTable.getPlayerNames()) {
            hit(blackjackTable, name);
        }
        OutputView.printDealerHitCount(blackjackTable.countHitDealer());

        // 결과
        Map<Name, HoldCards> players = blackjackTable.getAllPlayers();
        for (Name name : players.keySet()) {
            OutputView.printResult(name.getValue(), players.get(name).getCards(), players.get(name).countBestNumber());
        }
        OutputView.printGameResult(blackjackTable.getResult());
    }

    private void hit(BlackjackTable blackjackTable, Name name) {
        while (!blackjackTable.isFinishedPlayer(name)) {
            List<Card> cards = blackjackTable.hit(name, Command.find(InputView.inputCommand(name.getValue())));
            OutputView.printCard(name.getValue(), cards);
        }
    }
}
