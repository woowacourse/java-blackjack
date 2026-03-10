package domain.card;

import domain.card.vo.Card;
import domain.card.vo.Rank;
import domain.card.vo.Suit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class OneRandomDeckMaker implements DeckMaker {
    @Override
    public List<Card> make() {
        List<Card> cards = new LinkedList<>();
        for (Rank rank : Rank.values()) {
            addCards(rank, cards);
        }

        Collections.shuffle(cards);
        return cards;
    }

    private void addCards(Rank rank, List<Card> cards) {
        for (Suit suit : Suit.values()) {
            cards.addFirst(new Card(rank, suit));
        }
    }
}

