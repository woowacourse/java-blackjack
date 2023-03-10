package controller;

import domain.game.BlackjackGame;
import domain.strategy.RandomShuffleStrategy;
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
        GameParticipant gameParticipant = blackjackGame.getGameParticipant();

        for (Player player : gameParticipant.getPlayers()) {
            int amount = inputView.inputBetAmount(player.getPlayerName().getName());
            player.addAmount(amount);
        }
        blackjackGame.startHit();

        outputView.printPlayersInfoWhenGameStarted(gameParticipant.getDealer(), gameParticipant.getPlayers());

        letPlayerChoiceWhetherHit(gameParticipant.getPlayers());

        blackjackGame.updateBetAmount();
        outputView.printGameScore(gameParticipant.getDealer(), gameParticipant.getPlayers());

        outputView.printDealerRecord(gameParticipant.getDealer(), blackjackGame.getDealerRecord());
        outputView.printPlayerRecord(blackjackGame.getGameResultForAllPlayer());

        System.out.println(gameParticipant.getDealer().getRevenue());
        for (Player player : gameParticipant.getPlayers()) {
            System.out.println(player.getRevenue());
        }
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

}
