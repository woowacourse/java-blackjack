package controller;

import domain.BlackjackGame;
import domain.user.Player;
import java.util.function.Supplier;
import ui.InputView;
import ui.OutputView;
import util.ExceptionCounter;

public class BlackjackController {
    private final BlackjackGame blackjackGame;

    public BlackjackController() {
        this.blackjackGame = repeat(() ->
                BlackjackGame.of(InputView.readPlayersNameAndBettingAmount(), InputView.readDeckCount()));
    }

    public void run() {
        initialize();
        play();
        announceResult();
    }

    private void initialize() {
        this.blackjackGame.initializeGame();
        OutputView.printCardsStatus(this.blackjackGame.getDealer(), this.blackjackGame.getPlayers());
    }

    private void play() {
        this.blackjackGame.getPlayers().forEach(player -> giveCardUntilImpossible(player, this.blackjackGame));
        OutputView.announceAddCardToDealerIfHit(this.blackjackGame.hitOrStayByDealer());
    }

    private void announceResult() {
        OutputView.printCardsStatusWithScore(this.blackjackGame.getDealer(), this.blackjackGame.getPlayers());
        OutputView.printWinningAmountsOfAllPlayers(this.blackjackGame.calculateAllWinningAmounts());
    }

    private void giveCardUntilImpossible(Player player, BlackjackGame blackjackGame) {
        while (judgeWhetherDrawCard(player)) {
            blackjackGame.hitBy(player);
            OutputView.printCardsStatusOfUser(player);
        }
        if (player.canAdd()) {
            OutputView.printCardsStatusOfUser(player);
        }
    }

    private static boolean judgeWhetherDrawCard(Player player) {
        return player.canAdd() && InputView.readWhetherDrawCardOrNot(player);
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            ExceptionCounter.addCountHandledException();
            return repeat(supplier);
        }
    }
}
