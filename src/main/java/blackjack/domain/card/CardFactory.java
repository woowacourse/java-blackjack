package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardFactory {

    public List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            cards.addAll(createCardsBySuit(suit));
        }

        return cards;
    }

    private List<Card> createCardsBySuit(Suit suit) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Card(rank, suit))
                .toList();
    }
}
