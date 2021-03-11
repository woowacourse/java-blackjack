package blackjack.domain.card;

import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CardManager {

    private final Deck deck;

    private CardManager(Deck deck) {
        this.deck = deck;
    }

    public static CardManager create() {
        return new CardManager(Deck.create());
    }

    public Hands giveFirstHand() {
        return new Hands(deck.popTwoCards());
    }

    public Card giveCard() {
        return deck.popSingleCard();
    }

    public Players initiateGamers(List<String> playersNames, List<Integer> playersMoney) {
       List<Player> players = IntStream.range(0, playersNames.size())
                .mapToObj(i -> new Player(playersNames.get(i), playersMoney.get(i), giveFirstHand()))
                .collect(Collectors.toList());
       return new Players(players);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
