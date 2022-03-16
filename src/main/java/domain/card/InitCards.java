package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitCards {

    private static final int INIT_SIZE = 2;

    private final List<Card> intiCards;

    public InitCards(Deck deck) {
        this.intiCards = drawCards(deck);
    }

    private List<Card> drawCards(Deck deck) {
        return IntStream.range(0, INIT_SIZE)
                .mapToObj(i -> deck.draw())
                .collect(Collectors.toList());
    }

    public List<Card> getInitCards() {
        return Collections.unmodifiableList(intiCards);
    }

    public static List<List<Card>> generateInitCardsForPlayers(Deck deck, int playerCount) {
        return IntStream.range(0, playerCount)
                .mapToObj(i -> new InitCards(deck).getInitCards())
                .collect(Collectors.toList());
    }
}
