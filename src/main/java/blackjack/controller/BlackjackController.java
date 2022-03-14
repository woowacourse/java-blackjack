package blackjack.controller;

import blackjack.domain.Blackjack;
import blackjack.domain.Command;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run() {
        Blackjack blackjack = new Blackjack(InputView.inputNames());
        List<Participant> participants = blackjack.getParticipant();
        OutputView.printPlayersDefaultCard(participants);

        hit(blackjack);

        OutputView.printCardResult(blackjack.getCardResult(participants));
        OutputView.printGameResult(blackjack.getGameResult());
    }

    private void hit(Blackjack blackjack) {
        List<Player> players = blackjack.getPlayers();
        for (Player player : players) {
            hitPlayer(blackjack, player);
        }
        hitDealer(blackjack);
    }

    private void hitPlayer(Blackjack blackjack, Player player) {
        Command command;

        do {
            command = Command.find(InputView.inputCommand(player));
            blackjack.isHitThenReceiveCard(player, command);
            OutputView.printPlayerCards(player);
        } while (blackjack.canHit(player, command));
    }

    private void hitDealer(Blackjack blackjack) {
        while (blackjack.isDealerReceiveOneMoreCard()) {
            OutputView.printReceivingMoreCardOfDealer();
        }
    }
}
