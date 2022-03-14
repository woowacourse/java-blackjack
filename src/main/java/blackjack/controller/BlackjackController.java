package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Command;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputNames());
        List<Participant> participants = blackjackGame.getParticipant();
        OutputView.printPlayersDefaultCard(participants);

        hit(blackjackGame);

        OutputView.printCardResult(blackjackGame.getCardResult(participants));
        OutputView.printGameResult(blackjackGame.getGameResult());
    }

    private void hit(BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.getPlayers();
        for (Player player : players) {
            hitPlayer(blackjackGame, player);
        }
        hitDealer(blackjackGame);
    }

    private void hitPlayer(BlackjackGame blackjackGame, Player player) {
        Command command;

        do {
            command = Command.find(InputView.inputCommand(player));
            blackjackGame.isHitThenReceiveCard(player, command);
            OutputView.printPlayerCards(player);
        } while (blackjackGame.canHit(player, command));
    }

    private void hitDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.isDealerReceiveOneMoreCard()) {
            OutputView.printReceivingMoreCardOfDealer();
        }
    }
}
