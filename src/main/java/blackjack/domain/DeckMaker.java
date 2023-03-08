package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DeckMaker implements Shuffler {
    @Override
    public List<Card> createDeck() {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .flatMap(suit -> Stream.of(new Card(suit, rank)))
                ).collect(toList());
        Collections.shuffle(cards);
        return cards;
    }
}
