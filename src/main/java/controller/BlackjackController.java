package controller;

import domain.BlackjackGame;
import domain.user.Player;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    public void run() {
        BlackjackGame blackjackGame = generateGame();
        initialize(blackjackGame);
        play(blackjackGame);
        announceResult(blackjackGame);
    }

    private static BlackjackGame generateGame() {
        try {
            return new BlackjackGame(InputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return generateGame();
        }
    }

    private static void initialize(BlackjackGame blackjackGame) {
        blackjackGame.initializeGame();
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }

    private void play(BlackjackGame blackjackGame) {
        blackjackGame.getPlayers().forEach(player -> giveCardUntilImpossible(player, blackjackGame));
        OutputView.announceAddCardToDealerIfHit(blackjackGame.hitOrStayByDealer());
    }

    private static void announceResult(BlackjackGame blackjackGame) {
        OutputView.printCardsStatusWithScore(blackjackGame.getDealer(), blackjackGame.getPlayers());
        OutputView.printResults(blackjackGame.calculateAllResults());
    }

    private void giveCardUntilImpossible(Player player, BlackjackGame blackjackGame) {
        while (judgeWhetherDrawCard(player)){
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
}
