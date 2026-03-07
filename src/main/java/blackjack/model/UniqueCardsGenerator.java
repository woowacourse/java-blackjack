package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class UniqueCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> create() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }

        return cards;
    }

}
