package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShuffledCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> create() {
        List<Card> cards = new ArrayList<>();

        List<Rank> ranks = Arrays.asList(Rank.values());
        List<Suit> suits = Arrays.asList(Suit.values());

        for (Rank rank : ranks) {
            for (Suit suit : suits) {
                cards.add(new Card(rank, suit));
            }
        }
        return cards;
    }

}
