package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.CardDeck;
import blackjack.domain.user.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.user.Participants;
import blackjack.domain.user.Player;
import blackjack.domain.user.PlayerName;
import blackjack.view.InputView;
import blackjack.view.GameView;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackController {

    private final InputView inputView;
    private final GameView gameView;

    public BlackjackController(final InputView inputView, final GameView gameView) {
        this.inputView = inputView;
        this.gameView = gameView;
    }

    public void start() {
        BlackjackGame blackjackGame = enterParticipants();

        distributeInitialCards(blackjackGame);
        distributeAdditionalCards(blackjackGame);

        showFinalCards(blackjackGame);
        showWinLoseResult(blackjackGame);
    }

    private BlackjackGame enterParticipants() {
        List<PlayerName> names = inputView.readNames();
        return BlackjackGame.createByPlayerNames(CardDeck.shuffleCardDeck(), names);
    }

    private void distributeInitialCards(final BlackjackGame blackjackGame) {
        blackjackGame.initCardsToDealer();
        blackjackGame.initCardsToPlayer();
        Participants participants = blackjackGame.getParticipants();

        gameView.printStartGame(participants.getPlayerNames());
        gameView.printDealerCardResult(participants.getDealer().openInitialCards());
        for (Player player : participants.getPlayers()) {
            gameView.printPlayerCardResult(player.getName(), player.openCards());
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
            gameView.printPlayerCardResult(player.getName(), player.openCards());
        }
    }

    private void distributeAdditionalCardsToDealer(final BlackjackGame blackjackGame) {
        Dealer dealer = blackjackGame.getParticipants().getDealer();
        while (dealer.isPossibleToAdd()) {
            blackjackGame.addExtraCard(dealer);
            gameView.printAddExtraCardToDealer();
        }
    }

    private void showFinalCards(final BlackjackGame blackjackGame) {
        Participants participants = blackjackGame.getParticipants();
        Dealer dealer = participants.getDealer();

        gameView.printDealerFinalCardResult(dealer.calculateDenominations(), dealer.openCards());
        for (Player player : participants.getPlayers()) {
            gameView.printPlayerFinalCardResult(player.getName(), player.calculateDenominations(),
                player.openCards());
        }
    }

    private void showWinLoseResult(final BlackjackGame blackjackGame) {
        Map<GameResult, Integer> dealerResult = blackjackGame.calculateStatisticsForDealer();
        Map<Player, GameResult> playerResults = blackjackGame.calculateStatisticsForPlayer();

        gameView.printResultTitle();
        gameView.printDealerResult(dealerResult);
        for (Entry<Player, GameResult> playerResult : playerResults.entrySet()) {
            gameView.printPlayerResult(playerResult.getKey().getName(), playerResult.getValue());
        }
    }

    private void handleExtraCardError(final Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            gameView.printErrorMessage(e);
        }
    }
}
