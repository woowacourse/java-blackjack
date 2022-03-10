package blackjack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = new BlackjackGame(InputView.inputNames());
        List<Player> players = blackjackGame.getPlayers();
        List<Participant> participants = blackjackGame.getParticipant();
        OutputView.printPlayersDefaultCard(participants);

        for (Player player : players) {
            hit(blackjackGame, player);
        }

        if (blackjackGame.isDealerReceiveOneMoreCard()) {
            OutputView.printReceivingMoreCardOfDealer();
        }

        OutputView.printCardResult(getCardResult(participants));
        OutputView.printGameResult(blackjackGame.getGameResult());
    }

    private static void hit(BlackjackGame blackjackGame, Player player) {
        String command = InputView.inputCommand(player);
        if (Command.find(command) == Command.NO) {
            return;
        }

        do {
            command = InputView.inputCommand(player);
            blackjackGame.receiveOneMoreCard(player);
            OutputView.printPlayerCards(player);
        } while (blackjackGame.isHit(player, command));
    }

    private static Map<Participant, Integer> getCardResult(List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(participant -> participant, Participant::countCards));
    }
}
