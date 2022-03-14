package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        BlackjackTable blackjackTable = BlackjackController.createBlackjack(InputView.inputNames());
        OutputView.printPlayersDefaultCard(BlackjackController.getParticipants(blackjackTable));

        hit(blackjackTable);

        OutputView.printCardResult(BlackjackController.getCardResult(blackjackTable));
        OutputView.printGameResult(BlackjackController.getGameResult(blackjackTable));
    }

    private static void hit(BlackjackTable blackjackTable) {
        hitPlayers(blackjackTable);
        hitDealer(blackjackTable);
    }

    private static void hitPlayers(BlackjackTable blackjackTable) {
        List<Player> players = BlackjackController.getPlayers(blackjackTable);
        for (Player player : players) {
            hitPlayer(blackjackTable, player);
        }
    }

    private static void hitPlayer(BlackjackTable blackjackTable, Player player) {
        Command command = Command.find(InputView.inputCommand(player.getName()));
        if (command == Command.STAY) {
            OutputView.printPlayerCards(player);
            return;
        }
        moreHit(blackjackTable, player, command);
    }

    private static void moreHit(BlackjackTable blackjackTable, Player player, Command command) {
        while (blackjackTable.canHit(player, command)) {
            BlackjackController.hitPlayer(blackjackTable, player, command);
            OutputView.printPlayerCards(player);
            command = Command.find(InputView.inputCommand(player.getName()));
        }
    }

    private static void hitDealer(BlackjackTable blackjackTable) {
        BlackjackController.hitDealer(blackjackTable);
        OutputView.printReceivingMoreCardOfDealer();
    }
}
