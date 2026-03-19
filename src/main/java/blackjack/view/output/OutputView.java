package blackjack.view.output;

import blackjack.dto.CardResult;
import blackjack.dto.ProfitResult;
import java.util.List;

public interface OutputView {

    void printInitialSettingsDoneMessage(String dealerName, List<String> playersName);

    void printCards(String playerName, List<String> cards);

    void printCardsWithScore(CardResult cardResult);

    void printGetMoreCardsMessageForDealer(String dealerName);

    void printResult(ProfitResult profitResult);

    void println();

}
