package controller;

import domain.*;
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
        List<Player> players = people.getPlayers();
        Dealer dealer = people.getDealer();

        outputView.printPlayerInfo(dealer);
        outputView.printPlayersInfo(players);

        letPlayersHit(players);
        printGameInfo(players, dealer);

        outputView.printGameResult(blackjackGame.getGameResultForAllPlayer());
    }

    private void letPlayersHit(List<Player> players) {
        for (Player player : players) {
            hitByChoice(player);
        }
        blackjackGame.letDealerHitUntilThreshold();
    }

    private void printGameInfo(List<Player> players, Dealer dealer) {
        outputView.printResult(dealer);
        for (Player player : players) {
            outputView.printResult(player);
        }
    }

    private void hitByChoice(Player player) {
        if (needToQuit(player)) {
            return;
        }
        blackjackGame.hitFor(player.getPlayerName().getValue());
        outputView.printPlayerCard(player);

        hitByChoice(player);
    }

    private boolean needToQuit(Player player) {
        if (blackjackGame.isBurst(player.getPlayerName().getValue())) {
            return true;
        }
        String choice = inputView.inputCardCommand(player.getPlayerName().getValue());
        return choice.equals("n");
    }

    private void initializeGame() {
        List<String> playersName = inputView.inputParticipantsName();
        blackjackGame = new BlackjackGame(playersName, "딜러", new RandomNumberGenerator());
    }
}
