package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeckGenerator {
    private static final List<Card> cards = new ArrayList<>();

    static {
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .forEach(cards::add);
    }

    public static CardDeck generate() {
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }
}
