package domain.card;

import java.util.LinkedList;
import java.util.List;

public class OneDeckMaker implements DeckMaker {
    @Override
    public List<Card> make() {
        List<Card> cards = new LinkedList<>();
        for (Rank rank : Rank.values()) {
            addCards(rank, cards);
        }

        return cards;
    }

    private void addCards(Rank rank, List<Card> cards) {
        for (Suit suit : Suit.values()) {
            cards.addFirst(new Card(rank, suit));
        }
    }
}

