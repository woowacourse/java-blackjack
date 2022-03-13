package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.domain.Blackjack;
import blackjack.domain.Command;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        Blackjack blackjack = BlackjackController.createBlackjack(InputView.inputNames());
        OutputView.printPlayersDefaultCard(BlackjackController.getParticipants(blackjack));

        hit(blackjack);

        OutputView.printCardResult(BlackjackController.getCardResult(blackjack));
        OutputView.printGameResult(BlackjackController.getGameResult(blackjack));
    }

    private static void hit(Blackjack blackjack) {
        hitPlayers(blackjack);
        hitDealer(blackjack);
    }

    private static void hitPlayers(Blackjack blackjack) {
        List<Player> players = BlackjackController.getPlayers(blackjack);
        for (Player player : players) {
            hitPlayer(blackjack, player);
        }
    }

    private static void hitPlayer(Blackjack blackjack, Player player) {
        Command command = Command.find(InputView.inputCommand(player.getName()));
        if (command == Command.STAY) {
            OutputView.printPlayerCards(player);
            return;
        }
        moreHit(blackjack, player, command);
    }

    private static void moreHit(Blackjack blackjack, Player player, Command command) {
        while (blackjack.canHit(player, command)) {
            BlackjackController.hitPlayer(blackjack, player, command);
            OutputView.printPlayerCards(player);
            command = Command.find(InputView.inputCommand(player.getName()));
        }
    }

    private static void hitDealer(Blackjack blackjack) {
        BlackjackController.hitDealer(blackjack);
        OutputView.printReceivingMoreCardOfDealer();
    }
}
