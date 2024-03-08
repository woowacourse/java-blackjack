package blackjack;

import blackjack.domain.CardFactory;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Game;
import blackjack.domain.Players;
import blackjack.view.InputView;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> playerNames = InputView.readName();
        CardFactory cardFactory = new CardFactory();
        Deck deck = new Deck(cardFactory.createBlackJackCard());
        Dealer dealer = new Dealer(deck);
        Game game = new Game(dealer, Players.convertTo(playerNames));

        game.play();
    }
}
