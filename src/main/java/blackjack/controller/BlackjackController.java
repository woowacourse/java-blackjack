package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

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
            betPlayers(blackjackGame);
            distributeInitialCards(blackjackGame);

            distributeAdditionalCardsToPlayers(blackjackGame);
            distributeAdditionalCardsToDealer(blackjackGame);

            showFinalCards(blackjackGame);
            showBetResult(blackjackGame);
        });
    }

    private BlackjackGame enterParticipants() {
        List<String> names = inputView.readNames();
        return BlackjackGame.createByPlayerNames(names);
    }

    private void betPlayers(BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
            int betAmount = inputView.readBetAmount(player.getName());
            player.bet(betAmount);
        }
    }

    private void distributeInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.initCardsToParticipants();
        List<String> playerNames = blackjackGame.getPlayers()
                .stream()
                .map(Player::getName)
                .toList();
        outputView.printStartGame(playerNames);

        outputView.printDealerCardResult(blackjackGame.getDealer().openInitialCards());
        for (Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerCardResult(player.getName(), player.openCards());
        }
    }

    private void distributeAdditionalCardsToPlayers(final BlackjackGame blackjackGame) {
        for (Player player : blackjackGame.getPlayers()) {
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
        Dealer dealer = blackjackGame.getDealer();
        outputView.printDealerFinalCardResult(dealer.calculateDenominations(), dealer.openCards());
        for (Player player : blackjackGame.getPlayers()) {
            outputView.printPlayerFinalCardResult(player.getName(), player.calculateDenominations(),
                    player.openCards());
        }
    }

    private void showBetResult(final BlackjackGame blackjackGame) {
        outputView.printBetResultTitle();
        outputView.printBetResultByDealer(blackjackGame.calculateDealerPayout().value());
        Dealer dealer = blackjackGame.getDealer();
        for (Player player : blackjackGame.getPlayers()) {
            outputView.printBetResultByPlayer(player.getName(), player.calculatePayout(dealer).value());
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
