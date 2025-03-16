package blackjack.view;

import blackjack.constant.MatchResult;
import blackjack.gambler.Dealer;
import blackjack.gambler.Player;
import blackjack.gambler.Players;
import java.util.Map;

public interface OutputView {

    void printInitialGameSettings(Players players, Dealer dealer);

    void printPlayerCards(Player player);

    void printPlayerIsOverBust(Player player);

    void printDealerOneMoreCardMessage();

    void printGameSummary(Players players, Dealer dealer);

    void printGameResult(Map<Player, MatchResult> playerResults);
}
