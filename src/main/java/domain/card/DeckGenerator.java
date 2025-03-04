package domain.card;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DeckGenerator {
    public static Deck generateDeck() {
        List<Card> cards = Arrays.stream(Suit.values())
                .flatMap(suit ->
                        Arrays.stream(Rank.values())
                                .map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
