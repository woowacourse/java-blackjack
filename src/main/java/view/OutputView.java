package view;

import domain.constant.MatchResult;
import domain.gambler.Dealer;
import domain.gambler.Player;
import domain.gambler.Players;
import java.util.Map;

public interface OutputView {

    void printInitialGameSettings(Players players, Dealer dealer);

    void printPlayerCards(Player player);

    void printPlayerIsOverBust(Player player);

    void printDealerOneMoreCardMessage();

    void printGameSummary(Players players, Dealer dealer);

    void printGameResult(Map<Player, MatchResult> playerResults);
}
