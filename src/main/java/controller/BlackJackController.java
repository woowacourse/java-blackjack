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
        initializeGame();
        startGame();
    }

    private void startGame() {
        blackjackGame.startHit();

        GameParticipant gameParticipant = blackjackGame.getGameParticipant();
        outputView.printPlayersInfoWhenGameStarted(gameParticipant.getDealer(), gameParticipant.getPlayers());

        letPlayersHit(gameParticipant.getPlayers());
        outputView.printGameScore(gameParticipant.getDealer(), gameParticipant.getPlayers());

        outputView.printDealerRecord(gameParticipant.getDealer(), blackjackGame.getDealerRecord());
        outputView.printPlayerRecord(blackjackGame.getGameResultForAllPlayer());
    }

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, "딜러", new RandomShuffleStrategy());
    }

    private void letPlayersHit(List<Player> players) {
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
        if (blackjackGame.isBurst(player)) {
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
