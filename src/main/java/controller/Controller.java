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
        BlackjackGame game = new BlackjackGame(dealer, players, new CardDeck(new CardGenerator().generate()));
        game.shuffleCardDeck();
        distributeInitialCard(dealer, players, game);
        selectAdditionalCard(players, game);
        addWhenUnderStandard(dealer, game);
        outputView.printCardsResult(dealer, players);
        outputView.printWinnerResult(game.getGameResult());
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
            outputView.printPlayerCardsInfo(player);
        } while (!player.isOverBlackJack() && command.equals(Command.YES));
    }

    private void selectByCommand(BlackjackGame game, Player player, Command command) {
        if (command.equals(Command.YES)) {
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
