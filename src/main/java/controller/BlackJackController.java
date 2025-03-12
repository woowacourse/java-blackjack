package controller;

import static view.Response.YES;

import domain.game.Game;
import domain.participants.BettingAmount;
import domain.participants.PlayerName;
import domain.participants.PlayerNames;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
        List<String> usernames = inputView.insertUsernames();
        PlayerNames playerNames = new PlayerNames(usernames);
        List<PlayerName> playerUsernames = playerNames.getPlayerNames();
        Map<PlayerName, BettingAmount> bettingAmountsInfo = getBettingAmountsInfo(playerUsernames);
        Game game = initializeGame(bettingAmountsInfo);
        for (PlayerName playerName : playerUsernames) {
            askPlayer(game, playerName);
        }
        askDealer(game);
        outputView.printFinalState(game.getPlayersInfo(), game.getDealer());
        outputView.printGameStatistics(game.getGameStatistics());
    }

    private Game initializeGame(Map<PlayerName, BettingAmount> bettingAmountsInfo) {
        Game game = new Game(bettingAmountsInfo);
        game.distributeStartingHands();
        outputView.printInitialState(game.getPlayersInfo(), game.getDealerOneCard());
        return game;
    }

    private void askPlayer(Game game, PlayerName playerName) {
        while (game.isPlayerDrawable(playerName) && inputView.getUserResponse(playerName.username()) == YES) {
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

    private Map<PlayerName, BettingAmount> getBettingAmountsInfo(List<PlayerName> playerNames) {
        return playerNames.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        playerName -> new BettingAmount(inputView.insertBettingAmount(playerName.username()))
                ));
    }
}
