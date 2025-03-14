package controller;

import domain.BettingCalculator;
import domain.BettingMoney;
import domain.Game;
import domain.GameResult;
import domain.PlayerName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        Map<PlayerName, BettingMoney> bettingResults = new LinkedHashMap<>();

        for (PlayerName playerName : playerNames) {
            BettingMoney bettingMoney = inputView.insertBettingMoney(playerName);
            bettingResults.put(playerName, bettingMoney);
        }

        Game game = showInitialState(playerNames);
        for (PlayerName playerName : playerNames) {
            askPlayer(game, playerName);
        }
        askDealer(game);
        outputView.printFinalState(game.getPlayersInfo(), game.getDealer());

        Map<PlayerName, GameResult> gameResults = game.getGameResults();

        BettingCalculator bettingCalculator = new BettingCalculator(gameResults, bettingResults);
        outputView.printFinalResult(bettingCalculator.getTotalPlayerProfit(), bettingCalculator.getTotalDealerProfit());
    }

    private Game showInitialState(List<PlayerName> playerNames) {
        Game game = Game.initialize(playerNames);
        outputView.printInitialState(game.getPlayersInfo(), game.getDealerOneCard());
        return game;
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
