package blackjack;

import blackjack.domain.Blackjack;
import blackjack.domain.Command;
import blackjack.domain.entry.Participant;
import blackjack.domain.entry.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack(InputView.inputNames());
        List<Participant> participants = blackjack.getParticipant();
        OutputView.printPlayersDefaultCard(participants);

        hit(blackjack);

        OutputView.printCardResult(getCardResult(participants));
        OutputView.printGameResult(blackjack.getGameResult());
    }

    private static void hit(Blackjack blackjack) {
        List<Player> players = blackjack.getPlayers();
        for (Player player : players) {
            hitPlayer(blackjack, player);
        }
        hitDealer(blackjack);
    }

    private static void hitPlayer(Blackjack blackjack, Player player) {
        Command command = Command.find(InputView.inputCommand(player));
        if (command == Command.STAY) {
            OutputView.printPlayerCards(player);
            return;
        }
        moreHit(blackjack, player, command);
    }

    private static void moreHit(Blackjack blackjack, Player player, Command command) {
        while (blackjack.canHit(player, command)) {
            blackjack.receiveOneMoreCard(player);
            OutputView.printPlayerCards(player);
            command = Command.find(InputView.inputCommand(player));
        }
    }

    private static void hitDealer(Blackjack blackjack) {
        if (blackjack.isDealerReceiveOneMoreCard()) {
            OutputView.printReceivingMoreCardOfDealer();
        }
    }

    private static Map<Participant, Integer> getCardResult(List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(participant -> participant, Participant::countCards));
    }
}
