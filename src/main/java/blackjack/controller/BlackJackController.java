package blackjack.controller;

import blackjack.model.blackjackgame.BlackJackGame;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfits;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.vo.Money;
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
        List<Player> players = preparePlayers();
        BlackJackGame blackJackGame = prepareBlackJackGame(dealer, players);
        outputView.printDistributedCardsInfo(blackJackGame);

        executeMultipleTurns(players, blackJackGame);
        outputView.printFinalScore(blackJackGame);
        printGameResults(blackJackGame);
    }

    private List<Player> preparePlayers() {
        List<Player> players = inputView.readPlayers();
        betMoney(players);
        return players;
    }

    private void betMoney(List<Player> players) {
        for (Player player : players) {
            Money money = inputView.readBetAmount(player);
            player.betMoney(money);
        }
    }

    private BlackJackGame prepareBlackJackGame(Dealer dealer, List<Player> players) {
        BlackJackGame blackJackGame = new BlackJackGame(dealer, players);
        blackJackGame.distributeCards();
        return blackJackGame;
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
        finishTurnWithCommandNo(player, blackJackGame);
    }

    private void finishTurnWithCommandNo(Player player, BlackJackGame blackJackGame) {
        if (player.canHit()) {
            blackJackGame.finishTurn(player);
        }
    }

    private boolean checkDrawCardState(Player player) {
        if (player.getState().isBlackJack()) {
            outputView.printBlackJackState(player);
            return player.canHit();
        }
        if (!player.canHit()) {
            outputView.printBustState();
        }
        return player.canHit();
    }

    private void printGameResults(BlackJackGame blackJackGame) {
        PlayerProfits playerProfits = blackJackGame.calculatePlayerProfits();
        Money dealerProfit = blackJackGame.calculateDealerProfit(playerProfits);
        outputView.printGameResults(playerProfits, dealerProfit);
    }
}
