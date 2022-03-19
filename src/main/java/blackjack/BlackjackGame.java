package blackjack;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.Dealer;
import blackjack.domain.entry.Name;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    public void start() {
        // 카드 분배
        BlackjackTable blackjackTable = new BlackjackTable(InputView.inputBettingMoney());
        OutputView.printDealerCards(blackjackTable.getDealer(), blackjackTable.getPlayers());

        // 플레이어 Hit
        for (Name name : blackjackTable.getPlayerNames()) {
            hit(blackjackTable, name);
        }

        // 딜러 Hit
        OutputView.printReceivingMoreCardOfDealer(blackjackTable.countHitDealer());

        // 결과
        Dealer dealer = blackjackTable.getDealer();
        Map<Name, HoldCards> players = blackjackTable.getPlayers();
        OutputView.printResult(dealer.getName(), dealer.getHoldCards().getCards(), dealer.getHoldCards().countBestNumber());
        for (Name name : players.keySet()) {
            OutputView.printResult(name.getValue(), players.get(name).getCards(), players.get(name).countBestNumber());
        }
        OutputView.printGameResult(blackjackTable.getResult());
    }

    private void hit(BlackjackTable blackjackTable, Name name) {
        while (!blackjackTable.isFinishedPlayer(name)) {
            List<Card> cards = blackjackTable.hit(name, Command.find(InputView.inputCommand(name.getValue())));
            OutputView.printPlayerCards(name.getValue(), cards);
        }
    }
}
