package controller;

import domain.BlackjackGame;
import domain.Deck;
import ui.InputView;
import ui.OutputView;

public class BlackjackController {
    private final BlackjackGame blackjackGame;
    private final Deck deck;

    public BlackjackController() {
        this.blackjackGame = new BlackjackGame(InputView.readPlayersName());
        this.deck = new Deck();
    }

    public void run() {
        this.blackjackGame.initGame(deck);
        OutputView.printCardsStatus(blackjackGame.getDealer(), blackjackGame.getPlayers());
    }
}
