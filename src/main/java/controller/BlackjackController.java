package controller;

import java.util.List;
import java.util.function.Supplier;
import model.betting.Bet;
import model.game.BlackjackGame;
import model.game.HitChoice;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import model.result.CardDto;
import model.result.CardsDto;
import model.result.ProfitsDto;
import model.result.ScoresDto;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    public void run() {
        Players players = preparePlayers();
        prepareBets(players);
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame();

        CardsDto participantCards = blackjackGame.dealInitialCards(dealer, players);
        OutputView.printInitialCards(participantCards);

        playersTurnAndPrintCards(players, blackjackGame);
        dealerTurnAndPrintCard(dealer, blackjackGame);

        ScoresDto participantScores = blackjackGame.finish(dealer, players);
        OutputView.printScores(participantScores);

        ProfitsDto participantProfits = blackjackGame.calculateProfit(dealer, players);
        OutputView.printProfits(participantProfits);
    }

    private Players preparePlayers() {
        return retryOnException(() -> {
            List<String> playerNames = InputView.askPlayerNames();
            return Players.from(playerNames);
        });
    }

    private void prepareBets(Players players) {
        for (Player player : players.getPlayers()) {
            Bet bet = prepareBet(player);
            player.setBet(bet);
        }
    }

    private Bet prepareBet(Player player) {
        return retryOnException(() -> {
            String amount = InputView.askBet(player);
            return new Bet(amount);
        });
    }

    private void playersTurnAndPrintCards(Players players, BlackjackGame blackjackGame) {
        players.getPlayers()
            .forEach(player -> askHitAndPrintCards(player, blackjackGame));
    }

    private void askHitAndPrintCards(Player player, BlackjackGame blackjackGame) {
        while (player.isPossibleHit() && prepareHitChoice(player).isHit()) {
            CardDto playerCard = blackjackGame.dealCardTo(player);
            OutputView.printCards(playerCard);
        }
    }

    private HitChoice prepareHitChoice(Player player) {
        return retryOnException(() -> {
            String hitChoice = InputView.askHitChoice(player);
            return HitChoice.findHitChoice(hitChoice);
        });
    }

    private void dealerTurnAndPrintCard(Dealer dealer, BlackjackGame blackjackGame) {
        boolean isDealerHit = blackjackGame.dealCardTo(dealer);
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
