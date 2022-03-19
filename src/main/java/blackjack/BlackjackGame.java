package blackjack;

import blackjack.domain.BlackjackTable;
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
        List<String> names = InputView.inputNames();
        Map<String, Integer> bettingPlayers = InputView.inputBettingMoney(names);

        // 카드 분배
        BlackjackTable table = new BlackjackTable(bettingPlayers);
        while (table.isReady()) {
            table.divideCard();
        }
        OutputView.printDealerCards(table.getDealer(), table.getPlayers());

        // 플레이어 추가 분배
        for (String name : names) {
            while (!table.isFinished(name)) {
                List<Card> cards = table.hit(new Name(name), InputView.inputCommand(name));
                OutputView.printPlayerCards(name, cards);
            }
        }

        // 딜러 추가 분배
        while (!table.isFinishedDealer()) {
            table.divideCardByDealer();
            OutputView.printReceivingMoreCardOfDealer();
        }

        // 결과
        Dealer dealer = table.getDealer();
        Map<Name, HoldCards> players = table.getPlayers();
        OutputView.printResult(dealer.getName(), dealer.getHoldCards().getCards(), dealer.getHoldCards().countBestNumber());
        for (Name name : players.keySet()) {
            OutputView.printResult(name.getValue(), players.get(name).getCards(), players.get(name).countBestNumber());
        }
        OutputView.printGameResult(table.getResult());
    }
}
