package blackjack.controller;

import blackjack.model.blackjackgame.BlackJackGame;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfit;
import blackjack.model.results.PlayerResult;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = inputView.readPlayers();
        betMoney(players);
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players);
        blackJackGame.distributeCards();
        outputView.printDistributedCardsInfo(blackJackGame);

        executeMultipleTurns(players, blackJackGame);
        outputView.printFinalScore(blackJackGame);
        printGameResults(blackJackGame);
    }

    private void betMoney(List<Player> players) {
        for (Player player : players) {
            int money = inputView.readBetAmount(player);
            player.betMoney(money);
        }
    }

    private void executeMultipleTurns(List<Player> players, BlackJackGame blackJackGame) {
        for (int index = 0; index < players.size(); index++) {
            Player player = players.get(index);
            drawCardWithCommand(player, blackJackGame, index);
        }
        if (blackJackGame.checkDealerState()) {
            blackJackGame.updateDealer();
            outputView.printDealerChange();
        }
    }

    private void drawCardWithCommand(Player player, BlackJackGame blackJackGame, int index) {
        while (checkDrawCardState(player) && inputView.readCommand(player) == Command.YES) {
            blackJackGame.update(index);
            outputView.printPlayerCardsInfo(blackJackGame, index);
        }
    }

    private boolean checkDrawCardState(Player player) {
        if (!player.canHit()) {
            outputView.printBustState();
        }
        return player.canHit();
    }

    private void printGameResults(BlackJackGame blackJackGame) {
        PlayerResult playerResult = blackJackGame.calculatePlayerResults();
        PlayerProfit playerProfit = playerResult.getPlayerProfit();
        int dealerProfit = blackJackGame.calculateDealerProfit(playerResult);
        outputView.printGameResults(playerProfit, dealerProfit);
    }
}
