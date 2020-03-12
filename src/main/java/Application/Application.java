package Application;

import controller.BlackJackGame;
import model.*;
import view.InputView;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.createCardList());
        PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());
        Players players = new Players(playerNames, deck);
        Dealer dealer = new Dealer(deck.draw(INITIAL_DRAW_COUNT));

        BlackJackGame.play(players, dealer, deck);
    }
}
