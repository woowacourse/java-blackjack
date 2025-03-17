package blackjack.view;

import blackjack.BlackjackTable;
import blackjack.constant.MatchResult;
import blackjack.gambler.Dealer;
import blackjack.gambler.Player;
import blackjack.gambler.Players;
import java.util.Map;

public interface OutputView {

    void printInitialGameSettings(BlackjackTable table);

    void printDealerOneMoreCardMessage();

    void printGameSummary(BlackjackTable gameTable);

    void printPlayerCards(BlackjackTable gameTable, String playerName);

    void printGameResult(BlackjackTable gameTable);
}
