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

        List<String> yesOrNo = List.of("y", "y", "n");

        for (Player player : players) {
            for (String answer : yesOrNo) {
                while (answer.equals("y")) {
                    player.putCard(deck.draw());
                }
            }
        }

        if (dealer.shouldHaveMoreCard()) {
            dealer.putCard(deck.draw());
        }
    }
}
