package controller;

import domain.BlackjackGame;
import domain.GameResult;
import domain.deck.CardDeck;
import domain.generator.CardGenerator;
import domain.participants.Player;
import domain.participants.Players;
import strategy.ShuffleCardsStrategy;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = setPlayers();
        GameResult gameResult = new GameResult(players);
        BlackjackGame game = new BlackjackGame(players,
                new CardDeck(new CardGenerator().generate(new ShuffleCardsStrategy())));
        setPlayersBetMoney(players, gameResult);
        distributeInitialCard(players, game);
        pollAdditionalCard(players, game);

        printResult(players, game, gameResult);
    }

    private Players setPlayers() {
        try {
            return new Players(inputView.readPlayerNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPlayers();
        }
    }

    private void setPlayersBetMoney(Players players, GameResult gameResult) {
        for (Player player : players.getPlayersWithOutDealer()) {
            outputView.printBettingMessage(player);
            gameResult.addBetMoney(player, inputView.readBetMoney());
        }
    }

    private void distributeInitialCard(Players players, BlackjackGame game) {
        game.distributeInitialCard();
        outputView.printInitialCards(players.findDealer(), players);
    }

    private void selectByPlayer(BlackjackGame game, Player player) {
        boolean command;
        do {
            command = setCommand(player);
            game.distributeByCommand(player, command);
            outputView.printPlayerCardsInfo(player);
        } while (player.canDrawCard(command));
    }

    private boolean setCommand(Player player) {
        try {
            return inputView.readCommand(player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setCommand(player);
        }
    }

    private void pollAdditionalCard(Players players, BlackjackGame game) {
        for (Player player : players.getPlayersWithOutDealer()) {
            selectByPlayer(game, player);
        }
        while (!players.findDealer().isOverDealerStandard()) {
            game.distributeDealer();
            outputView.printDistributeDealer(players.findDealer());
        }
    }

    private void printResult(Players players, BlackjackGame game, GameResult gameResult) {
        gameResult.calculatePlayersResult(players);
        outputView.printCardsResult(players.findDealer(), players);
        outputView.printWinnerResult(players, game, gameResult);
    }
}
