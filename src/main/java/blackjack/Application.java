package blackjack;

import blackjack.domain.BlackjackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(final String... args) {
        List<Player> players = inputPlayersByNameAndBettingMoney(InputView.inputParticipantsNames());
        BlackjackGame blackjackGame = BlackjackGame.create(players);
        blackjackGame.drawBaseCards();
        OutputView.showParticipantsHand(blackjackGame.getParticipant(), blackjackGame.getParticipants());

        processPlayerTurn(blackjackGame.getPlayers(), blackjackGame);
        processDealerTurn(blackjackGame, blackjackGame.getDealer());
        OutputView.printParticipantResult(blackjackGame.getParticipant(), blackjackGame.getParticipants());
        OutputView.printDealerProfit(blackjackGame.calculateDealerProfit());
        OutputView.printPlayersProfit(blackjackGame.calculatePlayerProfit());
    }

    private static List<Player> inputPlayersByNameAndBettingMoney(String names) {
        return Arrays.stream(names.split(","))
            .map(playerName -> Player.from(playerName, getBettingMoney(playerName)))
            .collect(Collectors.toList());
    }

    private static String getBettingMoney(String name) {
        return InputView.inputBettingMoney(name);
    }

    private static void processDealerTurn(BlackjackGame blackjackGame, Dealer dealer) {
        while (dealer.shouldReceive()) {
            blackjackGame.takeMoreCard();
            OutputView.printDealerHandDrawMessage();
        }
    }

    private static void processPlayerTurn(List<Player> players, BlackjackGame blackjackGame) {
        for (Player player : players) {
            String command = InputView.inputOneMoreCard(player);
            playerTurn(blackjackGame, player, command);
        }
    }

    private static void playerTurn(BlackjackGame blackjackGame, Player player, String command) {
        while (!player.isFinished() && command.equals("y")) {
            blackjackGame.takeMoreCard(player);
            OutputView.showPlayerHand(player.getParticipant());
        }
        OutputView.showPlayerHand(player.getParticipant());
        if (command.equals("n")) {
            player.stay();
        }
    }
}
