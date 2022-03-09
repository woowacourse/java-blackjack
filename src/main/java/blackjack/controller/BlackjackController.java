package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.RandomDeck;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.requestNames();
        BlackjackGame blackjackGame = new BlackjackGame();
        Deck deck = new RandomDeck();
        Dealer dealer = blackjackGame.createDealer(deck);
        Players players = blackjackGame.createPlayers(names, deck);
        ResultView.printInitCard(dealer, players);
    }
}
