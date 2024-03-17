package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public class CardFactory {

    public List<Card> createCards() {
        return Arrays.stream(Suit.values())
                .map(this::createCardsBySuit)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Card> createCardsBySuit(Suit suit) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Card(rank, suit))
                .toList();
    }
}
