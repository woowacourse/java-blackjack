package Application;

import controller.BlackJackGame;
import model.*;
import view.InputView;

public class Application {
    public static final int INITIAL_DRAW_COUNT = 2;

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.createCardList());
        PlayerName names = new PlayerName(InputView.inputNames());
        Players players = new Players(names.getNames(), deck);
        Dealer dealer = new Dealer(deck.draw(INITIAL_DRAW_COUNT));

        BlackJackGame.play(players, dealer, deck);
    }
}
