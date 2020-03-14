package controller;

import domain.card.CardsFactory;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.Answer;
import view.InputView;
import view.OutputView;

public class GameController {
    private static final int ADD_CARD_SIZE = 1;

    public void run() {
        Gamers gamers = new Gamers(InputView.inputAsPlayerName(), new Dealer());
        Deck deck = new Deck(CardsFactory.getCards());
        gamers.initCard(deck);
        OutputView.printInitCardGuide(gamers);
        OutputView.printGamersCard(gamers);
        addCardAtGamers(gamers, deck);
        OutputView.printGameResults(gamers);
    }

    private void addCardAtGamers(Gamers gamers, Deck deck) {
        addCardAtPlayers(gamers, deck);
        gamers.getDealer().addCardAtDealer(deck);
        OutputView.printAddCardAtDealer();
    }

    private void addCardAtPlayers(Gamers gamers, Deck deck) {
        gamers.getPlayers()
                .forEach(player -> drawCardOfPlayer(deck, player));
    }

    private void drawCardOfPlayer(Deck deck, Player player) {
        while (player.isDrawable()
                && Answer.findAnswer(InputView.inputAsDrawable(player)).isYes()) {
            player.addCard(deck.popCard(ADD_CARD_SIZE));
            OutputView.printGamerCard(player);
        }
    }
}
