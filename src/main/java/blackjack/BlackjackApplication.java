package blackjack;

import blackjack.card.CardDeck;
import blackjack.game.BlackjackGame;
import blackjack.game.GameResult;
import blackjack.user.Dealer;
import blackjack.user.Participants;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import blackjack.view.InputView;
import blackjack.view.GameView;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BlackjackApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        GameView gameView = new GameView();

        BlackjackGame blackjackGame = enterParticipants(inputView);

        distributeInitialCards(blackjackGame, gameView);
        distributeAdditionalCards(blackjackGame, inputView, gameView);

        showFinalCards(blackjackGame, gameView);
        showWinLoseResult(blackjackGame, gameView);
    }

    private static BlackjackGame enterParticipants(final InputView inputView) {
        try {
            List<PlayerName> names = inputView.readNames();
            return BlackjackGame.createByPlayerNames(CardDeck.shuffleCardDeck(), names);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return enterParticipants(inputView);
        }
    }

    private static void distributeInitialCards(final BlackjackGame blackjackGame,
        final GameView gameView) {
        blackjackGame.initCardsToDealer();
        blackjackGame.initCardsToPlayer();

        gameView.printInitialCardResults(blackjackGame.getParticipants());
    }

    private static void distributeAdditionalCards(final BlackjackGame blackjackGame,
        final InputView inputView, final GameView gameView) {
        Participants participants = blackjackGame.getParticipants();

        for (Player player : participants.getPlayers()) {
            distributeAdditionalCardsToPlayer(player, blackjackGame, inputView, gameView);
        }
        distributeAdditionalCardsToDealer(blackjackGame, gameView);
    }

    private static void distributeAdditionalCardsToPlayer(final Player player,
        final BlackjackGame blackjackGame, final InputView inputView, final GameView gameView) {
        handleExtraCardError(() -> {
            while (inputView.readGetOneMore(player.getName())) {
                blackjackGame.addExtraCard(player);
                gameView.printPlayerCardResult(player);
            }
        });
    }

    private static void distributeAdditionalCardsToDealer(final BlackjackGame blackjackGame,
        final GameView gameView) {
        Dealer dealer = blackjackGame.getParticipants().getDealer();
        handleExtraCardError(() -> {
            while (dealer.isPossibleToAdd()) {
                blackjackGame.addExtraCard(dealer);
                gameView.printAddExtraCardToDealer();
            }
        });
    }

    private static void handleExtraCardError(final Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showFinalCards(final BlackjackGame blackjackGame, final GameView gameView) {
        Participants participants = blackjackGame.getParticipants();
        gameView.printFinalCardResults(participants);
    }

    private static void showWinLoseResult(final BlackjackGame blackjackGame, final GameView gameView) {
        Map<GameResult, Integer> dealerResult = blackjackGame.calculateStatisticsForDealer();
        Map<Player, GameResult> playerResults = blackjackGame.calculateStatisticsForPlayer();

        gameView.printResultTitle();
        gameView.printDealerResult(dealerResult);
        for (Entry<Player, GameResult> playerResult : playerResults.entrySet()) {
            gameView.printPlayerResult(playerResult.getKey().getName(), playerResult.getValue());
        }
    }
}
