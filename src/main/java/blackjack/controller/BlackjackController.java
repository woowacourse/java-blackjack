package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
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
        List<String> names = inputView.readNames();
        return BlackjackGame.createByPlayerNames(names);
    }

    private void distributeInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.initCardsToParticipants();
        outputView.printStartGame(blackjackGame.getPlayerNames());

        outputView.printDealerCardResult(blackjackGame.findDealer().openInitialCards());
        for (Player player : blackjackGame.findPlayers()) {
            outputView.printPlayerCardResult(player.getName(), player.openCards());
        }
    }

    private void distributeAdditionalCardsToPlayers(final BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.findPlayers()) {
            distributeAdditionalCardsToPlayer(blackjackGame, player);
        }
    }

    private void distributeAdditionalCardsToPlayer(final BlackjackGame blackjackGame, final Player player) {
        while (player.isPossibleToAdd() &&
                inputView.readGetOneMore(player.getName())) {
            blackjackGame.addExtraCard(player);
            outputView.printPlayerCardResult(player.getName(), player.openCards());
        }
    }

    private void distributeAdditionalCardsToDealer(final BlackjackGame blackjackGame) {
        while (blackjackGame.addExtraCardToDealer()) {
            outputView.printAddExtraCardToDealer();
        }
    }

    private void showFinalCards(final BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.findDealer();
        outputView.printDealerFinalCardResult(dealer.calculateDenominations(), dealer.openCards());
        for (Player player : blackjackGame.findPlayers()) {
            outputView.printPlayerFinalCardResult(player.getName(), player.calculateDenominations(), player.openCards());
        }
    }

    private void showWinLoseResult(final BlackjackGame blackjackGame) {
        Map<GameResult, Integer> dealerResult = blackjackGame.calculateStatisticsForDealer();
        Map<Player, GameResult> playerResults = blackjackGame.calculateStatisticsForPlayer();

        outputView.printResultTitle();
        outputView.printDealerResult(dealerResult);
        for (Entry<Player, GameResult> playerResult : playerResults.entrySet()) {
            outputView.printPlayerResult(playerResult.getKey().getName(), playerResult.getValue());
        }
    }

    private void handleException(final Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            System.exit(0);
        }
    }
}
