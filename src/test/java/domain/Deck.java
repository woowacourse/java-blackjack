package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        makeCards();
    }

    private void makeCards() {
        List<Card> allCards = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(rank, suit)))
                .toList();

        cards.addAll(allCards);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
