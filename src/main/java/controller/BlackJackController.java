package controller;

import domain.BettingMoney;
import domain.Game;
import domain.PlayerName;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public static final int DEFAULT_CARDS_PER_TURN = 1;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<PlayerName> playerNames = inputView.insertUsernames();
        Game game = initializeGame(playerNames);
        showInitialState(game);

        for (PlayerName playerName : playerNames) {
            askPlayer(game, playerName);
        }
        askDealer(game);

        outputView.printFinalState(game.getPlayers(), game.getDealer());
        outputView.printFinalResult(game.getPlayers(), game.calculateDealerProfit(), game.getDealer());
    }

    private Game initializeGame(List<PlayerName> playerNames) {
        List<BettingMoney> bettingResults = new ArrayList<>();

        for (PlayerName playerName : playerNames) {
            BettingMoney bettingMoney = inputView.insertBettingMoney(playerName);
            bettingResults.add(bettingMoney);
        }
        return Game.initialize(playerNames, bettingResults);
    }

    private void showInitialState(Game game) {
        outputView.printInitialState(game.getPlayers(), game.getDealerOneCard());
    }

    private void askPlayer(Game game, PlayerName playerName) {
        while (game.isPlayerDrawable(playerName) && inputView.isYes(playerName.username())) {
            game.giveCardToPlayer(playerName, DEFAULT_CARDS_PER_TURN);
            outputView.printGamerCards(playerName.username(), game.getPlayerCards(playerName));
        }
    }

    private void askDealer(Game game) {
        while (game.isDealerDrawable()) {
            game.giveCardToDealer(DEFAULT_CARDS_PER_TURN);
            outputView.printDealerDrawMoreCard();
        }
    }
}
