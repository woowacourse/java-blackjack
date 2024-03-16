package controller;

import static java.util.stream.Collectors.toMap;

import model.result.ParticipantCard;
import model.result.ParticipantCards;
import model.result.ParticipantProfits;
import model.result.ParticipantScores;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import model.betting.Bet;
import model.betting.PlayerBets;
import model.game.BlackjackGame;
import model.game.HitChoice;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Players players = preparePlayers();
        PlayerBets bets = prepareBets(players);
        BlackjackGame blackjackGame = new BlackjackGame();

        ParticipantCards participantCards = blackjackGame.dealInitialCards(players);
        OutputView.printInitialCards(participantCards);

        playersTurnAndPrintCards(players, blackjackGame);
        dealerTurnAndPrintCard(blackjackGame);

        ParticipantScores participantScores = blackjackGame.finish(players);
        OutputView.printScores(participantScores);

        ParticipantProfits participantProfits = blackjackGame.calculateProfit(players, bets);
        OutputView.printProfits(participantProfits);
    }

    private Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = InputView.askPlayerNames();
            return Players.from(playerNames);
        });
    }

    private PlayerBets prepareBets(Players players) {
        Map<String, Bet> betMoneys = players.getPlayers()
            .stream()
            .collect(toMap(Player::getName, this::prepareBet));
        return new PlayerBets(betMoneys);
    }

    private Bet prepareBet(Player player) {
        return retryOnException(() -> {
            int amount = InputView.askBet(player);
            return new Bet(amount);
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

    private static <T> T retryOnException(Supplier<T> retryOperation) {
        try {
            return retryOperation.get();
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return retryOnException(retryOperation);
        }
    }
}
