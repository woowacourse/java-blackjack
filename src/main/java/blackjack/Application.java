package blackjack;

import static java.util.stream.Collectors.*;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.view.InputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Deck deck = new Deck(Card.VALUES);

        List<String> names = InputView.getNames();
        List<Player> players = names.stream()
                .map(name -> new Player(name, deck.getInitCards()))
                .collect(toList());

        Dealer dealer = new Dealer(deck.getInitCards());
    }
}
