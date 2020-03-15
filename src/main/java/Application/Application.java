package Application;

import controller.BlackJackGame;
import model.*;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(CardFactory.createCardList());
        PlayerNames playerNames = new PlayerNames(InputView.inputPlayerNames());
        Players players = new Players(playerNames, deck);
        Dealer dealer = new Dealer(deck);

        BlackJackGame.play(players, dealer, deck);
    }
}
