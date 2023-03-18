import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.Number;
import domain.card.RandomShuffleStrategy;
import domain.game.BlackJackGame;
import domain.people.Player;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = initGame();
        bet(blackJackGame);
        deal(blackJackGame);
        play(blackJackGame);
        showResults(blackJackGame);
    }

    private static BlackJackGame initGame() {
        try {
            List<String> names = InputView.requestNames();
            return BlackJackGame.from(names, new RandomShuffleStrategy());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return initGame();
        }
    }

    private static void bet(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.fetchPlayers()) {
            int betAmount = readBetAmount(player);
            blackJackGame.assignBetAmount(player, betAmount);
        }
    }

    private static int readBetAmount(Player player) {
        try {
            OutputView.printEmptyLine();
            return InputView.requestBetAmount(player.fetchPlayerName());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readBetAmount(player);
        }
    }

    private static void deal(BlackJackGame blackJackGame) {
        blackJackGame.dealCardsToParticipants();

        OutputView.printDealFinishMessage(blackJackGame.getPlayers(), Number.INIT_HAND_COUNT.get());

        printInitialHands(blackJackGame);
        printBlackJackPlayer(blackJackGame);
    }

    private static void printInitialHands(BlackJackGame blackJackGame) {
        OutputView.printDealerHand(blackJackGame.getDealer());

        for (Player player : blackJackGame.fetchPlayers()) {
            OutputView.printPlayerHand(player);
        }
        OutputView.printEmptyLine();
    }

    private static void printBlackJackPlayer(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.fetchPlayers()) {
            printIfBlackJack(blackJackGame, player);
        }
    }

    private static void printIfBlackJack(BlackJackGame blackJackGame, Player player) {
        if (blackJackGame.hasBlackJack(player)) {
            OutputView.printPlayerHasBlackJack(player);
        }
    }

    private static void play(BlackJackGame blackJackGame) {
        playPlayerTurns(blackJackGame);
        playDealerTurn(blackJackGame);
    }

    private static void playPlayerTurns(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.fetchNoBlackJackPlayers()) {
            playTurn(blackJackGame, player);
            printIfBust(blackJackGame, player);
        }
    }

    private static void playTurn(BlackJackGame blackJackGame, Player player) {
        String hitRequest;
        do {
            hitRequest = readDrawingCardRequest(player);

            blackJackGame.hitOrStay(hitRequest, player);

            OutputView.printPlayerHand(player);
        } while (!(blackJackGame.isBust(player) || blackJackGame.isTurnOver(hitRequest)));
    }

    private static String readDrawingCardRequest(Player player) {
        try {
            return InputView.requestDrawingCard(player);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return readDrawingCardRequest(player);
        }
    }

    private static void printIfBust(BlackJackGame blackJackGame, Player player) {
        if (blackJackGame.isBust(player)) {
            OutputView.printPlayerIsBust(player);
        }
    }

    private static void playDealerTurn(BlackJackGame blackJackGame) {
        while (blackJackGame.shouldDealerHit()) {
            blackJackGame.dealerHit();
            OutputView.printDealerPickCardMessage();
        }
    }

    private static void showResults(BlackJackGame blackJackGame) {
        printScores(blackJackGame);
        printResults(blackJackGame);
    }

    private static void printScores(BlackJackGame blackJackGame) {
        OutputView.printDealerHandValue(blackJackGame.getDealer());

        for (Player player : blackJackGame.fetchPlayers()) {
            OutputView.printPlayerHandValue(player);
        }
    }

    private static void printResults(BlackJackGame blackJackGame) {
        OutputView.printResultInfo();

        LinkedHashMap<Player, Integer> playersAndProfits = new LinkedHashMap<>();

        printDealerResult(blackJackGame, playersAndProfits);
        printPlayersResults(playersAndProfits);
    }

    private static void printDealerResult(BlackJackGame blackJackGame,
        LinkedHashMap<Player, Integer> playersAndProfits) {
        int dealerProfit = calculateDealerProfit(blackJackGame, playersAndProfits);
        OutputView.printDealerResult(blackJackGame.getDealer(), dealerProfit);
    }

    private static int calculateDealerProfit(BlackJackGame blackJackGame,
        LinkedHashMap<Player, Integer> playersAndProfits) {
        for (Player player : blackJackGame.fetchPlayers()) {
            playersAndProfits.put(player, blackJackGame.fetchPlayerProfit(player));
        }
        return -playersAndProfits.values().stream().mapToInt(profit -> profit).sum();
    }

    private static void printPlayersResults(LinkedHashMap<Player, Integer> playersAndProfits) {
        for (Map.Entry<Player, Integer> playerAndProfit : playersAndProfits.entrySet()) {
            OutputView.printPlayerResult(playerAndProfit.getKey(), playerAndProfit.getValue());
        }
    }
}
