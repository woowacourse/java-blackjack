package controller;

import domain.BettingMoney;
import domain.Game;
import domain.PlayerName;
import domain.PlayerNames;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<PlayerName> rawPlayerNames = inputView.insertUsernames();
        PlayerNames playerNames = new PlayerNames(rawPlayerNames);
        Game game = initializeGame(playerNames.getPlayerNames());

        showInitialState(game);
        processGameTurn(game, playerNames);
        showFinalState(game);
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

    private void showFinalState(Game game) {
        outputView.printFinalState(game.getPlayers(), game.getDealer());
        outputView.printFinalResult(game.getPlayerProfits(), game.getDealerProfit());
    }

    private void processGameTurn(Game game, PlayerNames playerNames) {
        for (PlayerName playerName : playerNames.getPlayerNames()) {
            askPlayer(game, playerName);
        }
        askDealer(game);
    }

    private void askPlayer(Game game, PlayerName playerName) {
        while (game.isPlayerDrawable(playerName) && inputView.requestHitOrStand(playerName.username()).isHit()) {
            game.giveDefaultCardsToPlayer(playerName);
            outputView.printGamerCards(playerName.username(), game.getPlayerCards(playerName));
        }
    }

    private void askDealer(Game game) {
        while (game.isDealerDrawable()) {
            game.giveDefaultCardsToDealer();
            outputView.printDealerDrawMoreCard();
        }
    }
}
