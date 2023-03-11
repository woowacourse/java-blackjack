package controller;

import domain.game.BlackjackGame;
import domain.strategy.RandomShuffleStrategy;
import domain.user.Dealer;
import domain.user.GameParticipant;
import domain.user.Player;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private BlackjackGame blackjackGame;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            initializeGame();
            startGame();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
        }
    }

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, new RandomShuffleStrategy());
    }

    private void startGame() {
        List<Player> players = blackjackGame.getGameParticipant().getPlayers();
        Dealer dealer = blackjackGame.getGameParticipant().getDealer();

        startBet(players);
        startHit();
        outputView.printPlayersInfoWhenGameStarted(dealer, players);

        letPlayerChoiceWhetherHit(players);
        blackjackGame.updateBetAmount();
        printGameResult(players, dealer);
    }

    private void startBet(final List<Player> players) {
        for (Player player : players) {
            int amount = inputView.inputBetAmount(player.getPlayerName().getName());
            player.addAmount(amount);
        }
    }

    private void startHit() {
        blackjackGame.startHit();
    }

    private void letPlayerChoiceWhetherHit(List<Player> players) {
        for (Player player : players) {
            hitByPlayerChoice(player);
        }
        hitByDealer();
    }

    private void hitByPlayerChoice(Player player) {
        if (needToQuit(player)) {
            return;
        }
        blackjackGame.hitFor(player);
        outputView.printPlayerCardWithName(player);

        hitByPlayerChoice(player);
    }

    private boolean needToQuit(Player player) {
        if (player.isBurst()) {
            return true;
        }

        String frontCommand = inputView.inputCardCommand(player.getPlayerName().getName());
        ClientCommand clientCommand = ClientCommand.of(frontCommand);
        return clientCommand.isQuit();
    }

    private void hitByDealer() {
        if (blackjackGame.dealerNeedsHit()) {
            outputView.printDealerHitMessage();
            blackjackGame.letDealerHitUntilThreshold();
        }
    }

    private void printGameResult(final List<Player> players, final Dealer dealer) {
        outputView.printGameScore(dealer, players);
        outputView.printRevenueForAll(dealer, players);
    }
}
