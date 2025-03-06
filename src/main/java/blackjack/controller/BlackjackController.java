package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Player;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        handleException(() -> {
            BlackjackGame blackjackGame = enterParticipants();
            distributeInitialCards(blackjackGame);

            distributeAdditionalCardsToPlayers(blackjackGame);
            distributeAdditionalCardsToDealer(blackjackGame);

            showFinalCards(blackjackGame);
            showWinLoseResult(blackjackGame);
        });
    }

    private BlackjackGame enterParticipants() {
        String input = inputView.readNames();
        List<String> names = InputParser.parseStringToList(input);
        return BlackjackGame.createByPlayerNames(names);
    }

    private void distributeInitialCards(BlackjackGame blackjackGame) {
        blackjackGame.initCardsToParticipants();
        outputView.printStartGame(blackjackGame.getPlayerNames());

        outputView.printDealerCardResult(blackjackGame.findDealer());
        for (Player player : blackjackGame.findPlayers()) {
            outputView.printPlayerCardResult(player);
        }
    }

    private void distributeAdditionalCardsToPlayers(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.findPlayers()) {
            distributeAdditionalCardsToPlayer(blackjackGame, player);
        }
    }

    private void distributeAdditionalCardsToPlayer(BlackjackGame blackjackGame, Player player) {
        while (player.isPossibleToAdd() &&
                inputView.readGetOneMore(player.getName())) {
            blackjackGame.addExtraCard(player);
            outputView.printPlayerCardResult(player);
        }
    }

    private void distributeAdditionalCardsToDealer(BlackjackGame blackjackGame) {
        while (blackjackGame.addExtraCardToDealer()) {
            outputView.printAddExtraCardToDealer();
        }
    }

    private void showFinalCards(BlackjackGame blackjackGame) {
        outputView.printDealerFinalCardResult(blackjackGame.findDealer());
        for (Player player : blackjackGame.findPlayers()) {
            outputView.printPlayerFinalCardResult(player);
        }
    }

    private void showWinLoseResult(BlackjackGame blackjackGame) {
        outputView.printResultTitle();
        outputView.printDealerResult(blackjackGame.calculateStatisticsForDealer());
        for (Player player : blackjackGame.findPlayers()) {
            outputView.printPlayerResult(player, blackjackGame.findDealer());
        }
    }

    private void handleException(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            System.exit(0);
        }
    }
}
