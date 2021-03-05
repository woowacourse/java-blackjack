package blackjack.domain.card;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final CardStack cards;

    private Deck(CardStack cards) {
        this.cards = cards;
    }

    public static Deck create() {
        return new Deck(CardStack.create());
    }

    public Hands makeInitialHands() {
        return new Hands(cards.getTwoCards());
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card giveCard() {
        return cards.getSingleCard();
    }

    public Gamers initiateGamers(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, makeInitialHands()))
                .collect(Collectors.toList());
        return new Gamers(players, new Dealer(makeInitialHands()));
    }
}
