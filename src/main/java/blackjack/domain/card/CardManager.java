package blackjack.domain.card;

import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public Players initiateGamers(List<String> playersNames, List<Integer> playersMoney) {
       List<Player> players = IntStream.range(0, playersNames.size())
                .mapToObj(i -> new Player(playersNames.get(i), playersMoney.get(i), giveFirstHand()))
                .collect(Collectors.toList());
       return new Players(players);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
