package blackjack.domain.card.factory;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.TrumpCard;
import java.util.ArrayList;
import java.util.List;

public class TrumpCardFactory implements CardFactory {

    @Override
    public List<Card> createCards() {
        List<Card> trumpCards = new ArrayList<>();

        trumpCards.addAll(createCards(Suit.SPADE));
        trumpCards.addAll(createCards(Suit.DIAMOND));
        trumpCards.addAll(createCards(Suit.HEART));
        trumpCards.addAll(createCards(Suit.CLOVER));

        return trumpCards;
    }

    private List<TrumpCard> createCards(Suit suit) {
        List<TrumpCard> trumpCards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            trumpCards.add(new TrumpCard(rank, suit));
        }

        return trumpCards;
    }
}
