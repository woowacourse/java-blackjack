package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<Card>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getTotalSum() {
        int aceNum = 0;
        int sum = 0;

        for (Card card : cards) {
            if (card.getRank().isAce()) {
                aceNum += 1;
                continue;
            }
            sum += card.getRank().getValue();
        }

        for (int i = aceNum; i > 0; i--) {
            sum += Rank.decideAceValue(sum, i);
        }

        return sum;
    }

}
