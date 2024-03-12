package controller;

import static view.Command.YES;

import domain.Deck;
import domain.DeckGenerator;
import domain.ExceptionHandler;
import domain.user.Dealer;
import domain.user.GameUsers;
import domain.user.Player;
import domain.user.Players;
import view.Command;
import view.InputView;
import view.OutputView;

public class BlackjackRunner {
    public void play() {
        Deck deck = new Deck(new DeckGenerator().generate());
        Players players = ExceptionHandler.handle(InputView::inputPlayers);
        GameUsers gameUsers = new GameUsers(players, deck);
        OutputView.printStartStatus(gameUsers);
        doPlayersTurn(players, deck);
        doDealersTurn(gameUsers.getDealer(), deck);
        printGameResult(gameUsers);
    }

    private void doPlayersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitOrStay(player, deck);
        }
    }

    private void doDealersTurn(Dealer dealer, Deck deck) {
        while (dealer.isAddable()) {
            dealer.addCard(deck.getNewCard());
            OutputView.printDealerHitMessage();
        }
    }

    private void hitOrStay(Player player, Deck deck) {
        while (!player.busted() && YES == inputValidatedCommand(player.getNameValue())) {
            player.addCard(deck.getNewCard());
            printByState(player);
        }
    }

    private static Command inputValidatedCommand(String nameValue) {
        return ExceptionHandler.handle(() -> InputView.inputAddCommand(nameValue));
    }

    private void printByState(Player player) {
        OutputView.printUserAndCards(player.getNameValue(), player.getAllCards());
        if (player.busted()) {
            OutputView.printBust();
        }
    }

    private void printGameResult(GameUsers gameUsers) {
        OutputView.printAllUserCardsAndSum(gameUsers);
        OutputView.printFinalResult(gameUsers.generatePlayerResults());
    }
}
