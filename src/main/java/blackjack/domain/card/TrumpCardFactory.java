package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrumpCardFactory {

    public List<TrumpCard> createCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();

        Arrays.stream(Suit.values())
                .forEach(suit -> trumpCards.addAll(createCardsBySuit(suit)));

        return trumpCards;
    }

    private List<TrumpCard> createCardsBySuit(Suit suit) {
        return Arrays.stream(Rank.values())
                .map(rank -> new TrumpCard(rank, suit))
                .toList();
    }
}
