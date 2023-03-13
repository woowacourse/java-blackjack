package controller;

import domain.*;
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
        Dealer dealer = new Dealer();
        Players players = new Players(inputView.readPlayerNames());
        BettingTable bettingTable = createBettingTable(players);
        BlackjackGame game = new BlackjackGame(dealer, players, new CardDeck(new CardDeckGenerator().generate()));

        startGame(dealer, players, game);

        outputView.printCardsResult(dealer, players);
        bettingTable.calculate(game.getGameResult().getPlayerResult());
        outputView.printWinnerResult(dealer, bettingTable);
    }


    private BettingTable createBettingTable(Players players) {
        BettingTable bettingTable = new BettingTable();
        for (Player player : players.getPlayers()) {
            bettingTable.add(player, new BettingMoney(inputView.readBettingMoney(player.getName())));
        }
        return bettingTable;
    }

    private void startGame(Dealer dealer, Players players, BlackjackGame game) {
        game.shuffleCardDeck();
        distributeInitialCard(dealer, players, game);
        selectAdditionalCard(players, game);
        addWhenUnderStandard(dealer, game);
    }

    private void distributeInitialCard(Dealer dealer, Players players, BlackjackGame game) {
        game.distributeInitialCard();
        outputView.printInitialCards(dealer, players);
    }

    private void selectAdditionalCard(Players players, BlackjackGame game) {
        for (Player player : players.getPlayers()) {
            this.selectByPlayer(game, player);
        }
    }

    private void selectByPlayer(BlackjackGame game, Player player) {
        Command command;
        do {
            command = Command.from(inputView.readCommand(player.getName()));
            selectByCommand(game, player, command);
            outputView.printPlayerCardsInformation(player);
        } while (!player.isOverBlackJack() && command == Command.YES);
    }

    private void selectByCommand(BlackjackGame game, Player player, Command command) {
        if (command == Command.YES) {
            game.distributePlayer(player);
        }
    }

    private void addWhenUnderStandard(Dealer dealer, BlackjackGame game) {
        while (!dealer.isOverStandard()) {
            game.distributeDealer();
            outputView.printDistributeDealer(dealer);
        }
    }

}
