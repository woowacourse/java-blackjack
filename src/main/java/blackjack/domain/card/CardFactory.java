package blackjack.domain.card;

import java.util.*;

public class CardFactory {

    public List<Card> createCards() {
        return Arrays.stream(Suit.values())
                .map(this::createCardsBySuit)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Card> createCardsBySuit(Suit suit) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Card(rank, suit))
                .toList();
    }
}
