package machine;

import domain.betting.Money;
import domain.game.BlackjackGame;
import domain.game.PlayerResults;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import strategy.RandomCardGenerator;
import view.InputView;
import view.OutputView;

public class BlackjackMachine {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame game = initializeGame();
        readBetAmount(game);
        distributeStartingCards(game);
        playPlayerTurns(game);
        playDealerTurn(game);
        printCardsAndScores(game);
        printResult(game);
    }

    private void readBetAmount(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            int rawBetMoney = inputView.readBetAmount(player.getName());
            Money betMoney = Money.betValueOf(rawBetMoney);
            game.getAccounts()
                .add(player, betMoney);
        }
    }

    private void distributeStartingCards(BlackjackGame game) {
        game.distributeStartingCards();
        outputView.printDistributionMessage(game);
        outputView.printStartingCardsOfAllParticipants(game);
    }

    private BlackjackGame initializeGame() {
        List<String> names = inputView.readNames();
        return BlackjackGame.of(Players.withNames(names), new RandomCardGenerator());
    }

    private void playPlayerTurns(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            playPlayerTurn(game, player);
        }
    }

    private void playPlayerTurn(BlackjackGame game, Player player) {
        while (player.isNotBust() && isHitRequested(player)) {
            game.giveOneCardTo(player);
            outputView.printNameAndCardsOfParticipant(player.getName(), player.getCards());
        }
        if (player.isBust()) {
            outputView.printBustMessage(player.getName());
            return;
        }
        outputView.printNameAndCardsOfParticipant(player.getName(), player.getCards());
    }

    private boolean isHitRequested(Player player) {
        return inputView.readHitOrStay(player) == HitStay.HIT;
    }

    private void playDealerTurn(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        if (dealer.isReceivable()) {
            game.giveOneCardTo(dealer);
            outputView.printDealerDrawMessage();
            playDealerTurn(game);
        }
    }

    private void printCardsAndScores(BlackjackGame game) {
        outputView.printFinalCardsAndScoresOfAllParticipants(game);
    }

    private void printResult(BlackjackGame game) {
        PlayerResults playerResults = PlayerResults.of(game.getPlayers(), game.getDealer());
        outputView.printWinLoseOfAllParticipants(game, playerResults);
    }
}
