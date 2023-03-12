package controller;

import domain.BettingMoney;
import domain.BlackjackGame;
import domain.Command;
import domain.GameResult;
import domain.deck.CardDeck;
import domain.generator.CardGenerator;
import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;
import strategy.ShuffleCardsStrategy;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        distributeInitialCard(players, game);
        pollAdditionalCard(players, game);
        printResult(players, game, gameResult);
    }

    private Players setPlayers() {
        try {
            List<String> names = inputView.readPlayerNames();
            return createPlayers(names);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPlayers();
        }
    }

    private Players createPlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> createPlayer(name))
                .collect(Collectors.toList());
        players.add(0,new Dealer());
        return new Players(players);
    }

    private Player createPlayer(String name) {
        outputView.printBettingMessage(name);
        return new Player(name, new BettingMoney(inputView.readBetMoney()));
    }

    private void distributeInitialCard(Players players, BlackjackGame game) {
        game.distributeInitialCard();
        outputView.printInitialCards(players.findDealer(), players);
    }

    private void selectByPlayer(BlackjackGame game, Player player) {
        Command command;
        do {
            command = setCommand(player);
            game.distributeByCommand(player, command);
            outputView.printPlayerCardsInfo(player);
        } while (player.canDrawCard(command));
    }

    private Command setCommand(Player player) {
        try {
            return Command.from(inputView.readCommand(player.getName()));
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
