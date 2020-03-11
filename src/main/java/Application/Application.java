package Application;

import controller.BlackJackGame;
import model.*;
import view.InputView;

public class Application {
    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.createCardList());
        PlayerName names = new PlayerName(InputView.inputNames());
        Players players = new Players(names.getNames(), deck);
        Dealer dealer = new Dealer(deck.initialDraw());

        BlackJackGame.play(players, dealer, deck);
    }
}
