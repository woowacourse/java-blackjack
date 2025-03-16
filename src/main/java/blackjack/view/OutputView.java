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


    void printGameResult(Map<Player, MatchResult> playerResults);

    void printGameSummary(BlackjackTable gameTable);

    void printPlayerCards(BlackjackTable gameTable, String playerName);

}
