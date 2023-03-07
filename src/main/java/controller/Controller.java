package controller;

import domain.*;
import domain.deck.CardDeck;
import domain.generator.CardGenerator;
import domain.participants.Dealer;
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
        BlackjackGame game = new BlackjackGame(players,
                new CardDeck(new CardGenerator().generate(new ShuffleCardsStrategy())));
        distributeInitialCard(players, game);
        pollPlayerAdditionalCard(players, game);
        pollDealerAdditionalCard(players, game);
        printResult(players, game);
    }

    private Players setPlayers() {
        try {
            return new Players(inputView.readPlayerNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setPlayers();
        }
    }

    private void distributeInitialCard(Players players, BlackjackGame game) {
        game.distributeInitialCard();
        outputView.printInitialCards(players.findDealer(), players);
    }

    private void pollPlayerAdditionalCard(Players players, BlackjackGame game) {
        for (Player player : players.getPlayersWithOutDealer()) {
            selectByPlayer(game, player);
        }
    }

    private void selectByPlayer(BlackjackGame game, Player player) {
        String command;
        do {
            command = setCommand(player);
            game.distributeByCommand(player, command);
            outputView.printPlayerCardsInfo(player);
        } while (player.canDrawCard(command));
    }

    private String setCommand(Player player){
        try{
            return inputView.readCommand(player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return setCommand(player);
        }
    }

    private void pollDealerAdditionalCard(Players players, BlackjackGame game) {
        Dealer dealer = players.findDealer();
        while (!dealer.isOverDealerStandard()) {
            game.distributeDealer();
            outputView.printDistributeDealer(dealer);
        }
    }

    private void printResult(Players players, BlackjackGame game) {
        Dealer dealer = players.findDealer();
        outputView.printCardsResult(dealer, players);
        outputView.printWinnerResult(players, game);
    }
}
