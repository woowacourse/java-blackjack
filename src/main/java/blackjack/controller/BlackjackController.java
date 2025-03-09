package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.user.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        BlackjackGame blackjackGame = handleInputPlayerNameError(this::enterParticipants);

        distributeInitialCards(blackjackGame);
        distributeAdditionalCards(blackjackGame);

        showFinalCards(blackjackGame);
        showWinLoseResult(blackjackGame);
    }

    private BlackjackGame enterParticipants() {
        List<String> names = inputView.readNames();
        return BlackjackGame.createByPlayerNames(names);
    }

    private void distributeInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.initCardsToDealer();
        blackjackGame.initCardsToPlayer();
        Participants participants = blackjackGame.getParticipants();

        outputView.printStartGame(participants.getPlayerNames());
        outputView.printDealerCardResult(participants.getDealer().openInitialCards());
        for (Player player : participants.getPlayers()) {
            outputView.printPlayerCardResult(player.getName(), player.openCards());
        }
    }

    private void distributeAdditionalCards(final BlackjackGame blackjackGame) {
        Participants participants = blackjackGame.getParticipants();
        for (Player player : participants.getPlayers()) {
            handleExtraCardError(() -> distributeAdditionalCardsToPlayer(blackjackGame, player));
        }
        handleExtraCardError(() -> distributeAdditionalCardsToDealer(blackjackGame));
    }

    private void distributeAdditionalCardsToPlayer(final BlackjackGame blackjackGame,
        final Player player) {
        while (inputView.readGetOneMore(player.getName())) {
            blackjackGame.addExtraCard(player);
            outputView.printPlayerCardResult(player.getName(), player.openCards());
        }
    }

    private void distributeAdditionalCardsToDealer(final BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getParticipants().getDealer();
        while (dealer.isPossibleToAdd()) {
            blackjackGame.addExtraCard(dealer);
            outputView.printAddExtraCardToDealer();
        }
    }

    private void showFinalCards(final BlackjackGame blackjackGame) {
        Participants participants = blackjackGame.getParticipants();
        Dealer dealer = participants.getDealer();

        outputView.printDealerFinalCardResult(dealer.calculateDenominations(), dealer.openCards());
        for (Player player : participants.getPlayers()) {
            outputView.printPlayerFinalCardResult(player.getName(), player.calculateDenominations(),
                player.openCards());
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

    private BlackjackGame handleInputPlayerNameError(final Supplier<BlackjackGame> action) {
        try {
            return action.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return enterParticipants();
        }
    }

    private void handleExtraCardError(final Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }
}
