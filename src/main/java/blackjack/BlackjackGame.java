package blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    public void start() {
        Deck deck = Deck.create();
        Dealer dealer = new Dealer(deck.draw(), deck.draw());

        List<String> playerNames = List.of("jason", "pobi");
        List<Player> players = playerNames.stream()
                .map(name -> new Player(name, deck.draw(), deck.draw()))
                .collect(Collectors.toList());
    }
}
