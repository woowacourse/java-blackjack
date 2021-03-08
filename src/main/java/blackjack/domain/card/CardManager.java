package blackjack.domain.card;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import java.util.List;
import java.util.stream.Collectors;

public class CardManager {

    private final Deck cards;

    private CardManager(Deck cards) {
        this.cards = cards;
    }

    public static CardManager create() {
        return new CardManager(Deck.create());
    }

    public Hands giveFirstHand() {
        return new Hands(cards.popTwoCards());
    }

    public Card giveCard() {
        return cards.popSingleCard();
    }

    public Gamers initiateGamers(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, giveFirstHand()))
                .collect(Collectors.toList());
        return new Gamers(players, new Dealer(giveFirstHand()));
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
