package blackjack;

import blackjack.domain.BlackjackTable;
import blackjack.domain.Command;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackGame {

    public void start() {
        BlackjackTable blackjackTable = new BlackjackTable(InputView.inputNames());
        OutputView.printParticipantsCard(blackjackTable.getParticipant());
        hit(blackjackTable);
        OutputView.printCardResult(blackjackTable.getCardResult());
        OutputView.printGameResult(blackjackTable.getGameResult());
    }

    private void hit(BlackjackTable blackjackTable) {
        hitPlayers(blackjackTable);
        hitDealer(blackjackTable);
    }

    private void hitPlayers(BlackjackTable blackjackTable) {
        List<Player> players = blackjackTable.getPlayers();
        for (Player player : players) {
            hitPlayer(blackjackTable, player);
        }
    }

    private void hitPlayer(BlackjackTable blackjackTable, Player player) {
        Command command = Command.find(InputView.inputCommand(player.getName()));
        if (command == Command.STAY) {
            OutputView.printPlayerCards(player);
            return;
        }
        moreHit(blackjackTable, player, command);
    }

    private void moreHit(BlackjackTable blackjackTable, Player player, Command command) {
        while (blackjackTable.canHit(player, command)) {
            blackjackTable.hit(player);
            OutputView.printPlayerCards(player);
            command = Command.find(InputView.inputCommand(player.getName()));
        }
    }

    private void hitDealer(BlackjackTable blackjackTable) {
        while (blackjackTable.needMoreCardByDealer()) {
            blackjackTable.hitDealer();
        }
        OutputView.printReceivingMoreCardOfDealer();
    }
}
