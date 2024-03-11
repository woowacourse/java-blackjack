package blackjack.domain.card.factory;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public List<Card> createCards() {
        List<Card> trumpCards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            trumpCards.addAll(createCards(suit));
        }

        return trumpCards;
    }

    private List<Card> createCards(Suit suit) {
        List<Card> trumpCards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            trumpCards.add(new Card(rank, suit));
        }

        return trumpCards;
    }
}
