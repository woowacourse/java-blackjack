package controller;

import domain.BlackJackGame;
import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Result;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    public BlackJackGameController() {
    }

    public void run() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = initPlayer(playerNames);
        Deck deck = new Deck();

        BlackJackGame blackJackGame = new BlackJackGame(deck, players, Dealer.create());
        blackJackGame.distributeInitialCards();

        OutputView.printGameInitialMessage(playerNames);

        OutputView.printInitialDealerCards(blackJackGame.getDealerCardsDto());
        for (Player player : players) {
            OutputView.printInitialPlayerCards(blackJackGame.getPlayerCardsDto(player));
        }

        playGame(blackJackGame, players);

        Result gameResult = blackJackGame.getGameResult();
        endGame(blackJackGame, players, gameResult);
    }

    private List<String> getPlayerNames() {
        return InputView.askPlayerNames();
    }

    private List<Player> initPlayer(List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::from)
                .toList();
        return players;
    }

    private void playGame(BlackJackGame blackJackGame, List<Player> players) {
        for (Player player : players) {
            while (blackJackGame.canPlayerReceiveCard(player)) {
                if (isStopGame(blackJackGame, player)) {
                    break;
                }
                blackJackGame.playGameWithPlayer(player);
                OutputView.printCards(blackJackGame.getPlayerCardsDto(player));
            }
        }
        if (blackJackGame.canDealerReceiveCard()) {
            blackJackGame.playGameWithDealer();
            OutputView.printDealerMessage();
        }
    }

    private static void endGame(BlackJackGame blackJackGame, List<Player> players,
                                Result gameResult) {
        OutputView.printFinalCards(blackJackGame.getDealerCardsDto());
        printFinalScores(blackJackGame, players);
        OutputView.printGameResult(gameResult.getGameResult());
    }

    private static void printFinalScores(BlackJackGame blackJackGame, List<Player> players) {
        for (Player player : players) {
            OutputView.printFinalCards(blackJackGame.getPlayerCardsDto(player));
        }
    }

    private boolean isStopGame(BlackJackGame blackJackGame, Player player) {
        if (!isContinue(InputView.askContinue(player.getName()))) {
            OutputView.printCards(blackJackGame.getPlayerCardsDto(player));
            return true;
        }
        return false;
    }

    private boolean isContinue(String response) {
        return response.equals("y");
    }
}
