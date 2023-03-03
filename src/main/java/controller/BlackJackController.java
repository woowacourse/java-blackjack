package controller;

import domain.game.BlackjackGame;
import domain.strategy.RandomNumberGenerator;
import domain.user.People;
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

    public void play() {
        initializeGame();
        blackjackGame.startHit();

        People people = blackjackGame.getPeople();
        outputView.printPlayersInfoWhenGameStarted(people.getDealer(), people.getPlayers());

        letPlayersHit(people.getPlayers());
        outputView.printGameScore(people.getDealer(), people.getPlayers());

        outputView.printDealerRecord(people.getDealer(), blackjackGame.getDealerRecord());
        outputView.printPlayerRecord(blackjackGame.getGameResultForAllPlayer());
    }

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, "딜러", new RandomNumberGenerator());
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
        blackjackGame.hitFor(player.getPlayerName().getValue());
        outputView.printPlayerCardWithName(player);

        hitByPlayerChoice(player);
    }

    private boolean needToQuit(Player player) {
        if (blackjackGame.isBurst(player.getPlayerName().getValue())) {
            return true;
        }
        String choice = inputView.inputCardCommand(player.getPlayerName().getValue());
        return choice.equals("n");
    }

    private void hitByDealer() {
        if (blackjackGame.dealerNeedsHit()) {
            outputView.printDealerHitMessage();
            blackjackGame.letDealerHitUntilThreshold();
        }
    }

}
