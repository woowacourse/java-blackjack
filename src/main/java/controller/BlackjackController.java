package controller;

import dto.ParticipantCard;
import dto.ParticipantCards;
import java.util.List;
import java.util.function.Supplier;
import model.game.BlackjackGame;
import model.game.HitChoice;
import model.participant.Player;
import model.participant.Players;
import model.result.DealerResult;
import model.result.GameResult;
import model.result.PlayerResults;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Players players = preparePlayers();
        BlackjackGame blackjackGame = new BlackjackGame();

        ParticipantCards participantCards = blackjackGame.dealInitialCards(players);
        OutputView.printInitialCards(participantCards);

        playersTurnAndPrintCards(players, blackjackGame);
        dealerTurnAndPrintCard(blackjackGame);

        GameResult gameResult = blackjackGame.finish(players);
        printGameResult(gameResult);
    }

    private Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = InputView.askPlayerNames();
            return Players.from(playerNames);
        });
    }

    private void playersTurnAndPrintCards(Players players, BlackjackGame blackjackGame) {
        players.getPlayers()
            .forEach(player -> askHitAndPrintCards(player, blackjackGame));
    }

    private void askHitAndPrintCards(Player player, BlackjackGame blackjackGame) {
        while (player.isPossibleHit() && prepareHitChoice(player).isHit()) {
            ParticipantCard playerCard = blackjackGame.dealCard(player);
            OutputView.printCards(playerCard);
        }
    }

    private HitChoice prepareHitChoice(Player player) {
        return retryOnException(() -> {
            String hitChoice = InputView.askHitChoice(player);
            return HitChoice.findHitChoice(hitChoice);
        });
    }

    private void dealerTurnAndPrintCard(BlackjackGame blackjackGame) {
        boolean isDealerHit = blackjackGame.dealerHitTurn();
        if (isDealerHit) {
            OutputView.printAfterDealerHit();
        }
    }

    private void printGameResult(GameResult gameResult) {
        PlayerResults playerResults = PlayerResults.from(gameResult);
        DealerResult dealerResult = DealerResult.from(playerResults);
        OutputView.printGameResult(gameResult, dealerResult, playerResults);
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
