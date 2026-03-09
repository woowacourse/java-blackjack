package service;

import domain.Game;
import domain.card.CardGenerator;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Players;
import java.util.List;
import view.OutputView;

public class BlackjackService {
    public Players makePlayers(List<String> playerNames) {
        return new Players(playerNames);
    }

    public Deck makeDeck() {
        return new Deck(CardGenerator.generateCards());
    }

    public Game makeGame(Players players) {
        return new Game(players, new Dealer());
    }

    public void initializeGame(Game game, Deck deck) {
        game.initializeGame(deck);
    }

    public void playPlayerTurn(Game game, String name, Deck deck, boolean shouldContinue) {
        game.playerHit(name, deck, shouldContinue);
    }

    public void playDealerTurn(Game game, Deck deck) {
        while (!game.isDealerBust()) {
            game.dealerHit(deck);
            OutputView.printDealerHit();
        }
    }
}
