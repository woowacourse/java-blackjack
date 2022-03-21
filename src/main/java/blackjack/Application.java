package blackjack;

import blackjack.domain.BlackjackGame;
import blackjack.domain.betting.BettingPlayer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static final String DELIMITER = ",";

    public static void main(final String... args) {
        List<Participant> players = inputPlayersByName(InputView.inputParticipantsNames());
        BlackjackGame blackjackGame = BlackjackGame.create(players);
        List<BettingPlayer> bettingPlayers = getBettingPlayers(blackjackGame.getPlayers());
        blackjackGame.drawBaseCards();
        OutputView.showParticipantsHand(blackjackGame.getDealer(), blackjackGame.getPlayers());

        processPlayerTurn(blackjackGame.getPlayers(), blackjackGame);
        processDealerTurn(blackjackGame, blackjackGame.getDealer());
        printGameResult(blackjackGame, bettingPlayers);
    }

    private static void printGameResult(BlackjackGame blackjackGame, List<BettingPlayer> bettingPlayers) {
        OutputView.printParticipantResult(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printDealerProfit(blackjackGame.calculateDealerProfit(bettingPlayers));
        OutputView.printPlayersProfit(blackjackGame.calculatePlayerProfit(bettingPlayers));
    }

    private static List<BettingPlayer> getBettingPlayers(List<Participant> players) {
        return players.stream()
            .map(player -> BettingPlayer.of(player, InputView.inputBettingMoney(player.getName())))
            .collect(Collectors.toList());
    }

    private static List<Participant> inputPlayersByName(String names) {
        return Arrays.stream(names.split(DELIMITER))
            .map(playerName -> new Player(playerName.trim()))
            .collect(Collectors.toList());
    }

    private static void processDealerTurn(BlackjackGame blackjackGame, Participant dealer) {
        while (dealer.shouldReceive()) {
            blackjackGame.takeMoreCard(dealer);
            OutputView.printDealerHandDrawMessage();
        }
    }

    private static void processPlayerTurn(List<Participant> players, BlackjackGame blackjackGame) {
        for (Participant player : players) {
            playerTurn(blackjackGame, player);
        }
    }

    private static void playerTurn(BlackjackGame blackjackGame, Participant player) {
        while (!player.isFinished() && InputView.inputOneMoreCard(player)) {
            blackjackGame.takeMoreCard(player);
            OutputView.showPlayerHand(player);
        }
        if (player.isFinished()) {
            OutputView.printBustMessage();
            return;
        }
        player.stay();
        OutputView.showPlayerHand(player);
    }
}
