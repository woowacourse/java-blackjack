package controller;

import domain.card.CardsFactory;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.AddCardAnswer;
import view.InputView;
import view.OutputView;

public class GameController {
    public void run() {
        Gamers gamers = new Gamers(InputView.inputAsPlayerName(), new Dealer());
        Deck deck = new Deck(CardsFactory.getInstance());
        gamers.initCard(deck);
        OutputView.printInitCardGuide(gamers);
        OutputView.printGamersCard(gamers);
        gamers.addCardAtGamers(deck);
        OutputView.printGameResults(gamers);
    }
}
