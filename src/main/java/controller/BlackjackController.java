package controller;

import java.util.List;
import java.util.function.Supplier;
import model.game.BlackjackGame;
import model.game.HitChoice;
import model.participant.Player;
import model.participant.Players;
import model.result.DealerResult;
import model.result.GameResult;
import model.result.PlayersResult;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Players players = preparePlayers();
        BlackjackGame blackjackGame = new BlackjackGame();

        blackjackGame.dealInitialCards(players);
        OutputView.printInitialCards(blackjackGame, players);

        askHitAndPrintCards(players, blackjackGame);
        dealerTurnAndPrint(blackjackGame);
        GameResult gameResult = blackjackGame.finish(players);

        DealerResult dealerResult = DealerResult.from(gameResult);
        PlayersResult playersResult = PlayersResult.from(gameResult);
        OutputView.printFinalScore(blackjackGame, players, gameResult);
        OutputView.printGameResult(dealerResult, playersResult);
    }

    private void dealerTurnAndPrint(BlackjackGame blackjackGame) {
        boolean isDealerHit = blackjackGame.dealerHitTurn();
        if (isDealerHit) {
            OutputView.printAfterDealerHit(blackjackGame.getDealer());
        }
    }

    private Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = InputView.askPlayerNames();
            return Players.from(playerNames);
        });
    }

    private void askHitAndPrintCards(Players players, BlackjackGame blackjackGame) {
        players.getPlayers()
            .forEach(player -> askHitAndPrintCards(blackjackGame, player));
    }

    private void askHitAndPrintCards(BlackjackGame blackjackGame, Player player) {
        while (player.isPossibleHit() && prepareHitChoice(player).isHit()) {
            blackjackGame.dealCard(player);
            OutputView.printPlayerCard(player);
        }
    }

    private HitChoice prepareHitChoice(Player player) {
        return retryOnException(() -> {
            String hitChoice = InputView.askHitChoice(player);
            return HitChoice.findHitChoice(hitChoice);
        });
    }

    private static <T> T retryOnException(Supplier<T> retryOperation) {
        try {
            return retryOperation.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return retryOnException(retryOperation);
        }
    }
}
