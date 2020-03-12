package controller;

import domain.card.CardFactory;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.YesOrNo;
import view.InputView;
import view.OutputView;

public class GameController {
    public void run() {
        Gamers gamers = new Gamers(InputView.inputAsPlayerName(), new Dealer());
        Deck deck = new Deck(CardFactory.getInstance());
        gamers.initCard(deck);
        OutputView.printInitCardGuide(gamers);
        OutputView.printGamersCard(gamers);
        addCardAtPlayers(gamers, deck);
        gamers.getDealer().addCardAtDealer(deck);
        OutputView.printAddCardAtDealer();
        OutputView.printCardsResultAndScore(gamers);
        OutputView.printPlayersWinOrLose(gamers.generateGameResults());
    }

    private void addCardAtPlayers(Gamers gamers, Deck deck) {
        gamers.getPlayers()
                .forEach(player -> drawCardOfPlayer(deck, player));
    }

    private void drawCardOfPlayer(Deck deck, Player player) {
        while (player.isDrawable()
                && YesOrNo.findYesOrNo(InputView.inputAsDrawable(player)).getDrawable()) {
            player.addCard(deck.popCard(1));
            OutputView.printGamerCard(player);
        }
    }
}
