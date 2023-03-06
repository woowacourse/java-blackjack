package controller;

import domain.*;
import strategy.ShuffleCardsStrategy;
import view.InputView;
import view.OutputView;

public class Controller {
    private static final String Y_COMMAND = "y";

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
        printResult(players,game);
    }

    private Players setPlayers(){
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
            command = inputView.readCommand(player.getName());
            game.distributeByCommand(player, command);
            outputView.printPlayerCardsInfo(player);
        } while (player.canSelectCard(command));
    }


    private void pollDealerAdditionalCard(Players players, BlackjackGame game) {
        Dealer dealer = players.findDealer();
        while (!dealer.isOverDealerStandard()) {
            game.distributeDealer();
            outputView.printDistributeDealer(dealer);
        }
    }

    private void printResult(Players players, BlackjackGame game){
        Dealer dealer = players.findDealer();
        outputView.printCardsResult(dealer, players);
        outputView.printWinnerResult(game.getDealerResult(), game.getPlayersResult());
    }
}
