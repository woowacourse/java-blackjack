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
        Dealer dealer = new Dealer();
        GameResult gameResult = new GameResult();
        BlackjackGame game = new BlackjackGame(dealer,players,
                new CardDeck(new CardGenerator().generate(new ShuffleCardsStrategy())));
        distributeInitialCard(dealer, players, game);
        pollAdditionalCard(dealer, players, game);
        printResult(dealer, players, gameResult);
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
        return new Players(players);
    }

    private Player createPlayer(String name) {
        outputView.printBettingMessage(name);
        return new Player(name, new BettingMoney(inputView.readBetMoney()));
    }

    private void distributeInitialCard(Dealer dealer, Players players, BlackjackGame game) {
        game.distributeInitialCard();
        outputView.printInitialCards(dealer, players);
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

    private void pollAdditionalCard(Dealer dealer, Players players, BlackjackGame game) {
        for (Player player : players.getPlayers()) {
            selectByPlayer(game, player);
        }
        while (!dealer.isOverDealerStandard()) {
            game.distributeDealer();
            outputView.printDistributeDealer(dealer);
        }
    }

    private void printResult(Dealer dealer, Players players, GameResult gameResult) {
        gameResult.calculatePlayersResult(dealer,players);
        outputView.printCardsResult(dealer, players);
        outputView.printWinnerResult(dealer,players, gameResult);
    }
}
