package blackjack.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private List<Card> cards;

    public Cards(final List<Card> cards){
        this.cards = new ArrayList<>(cards);
    }

    public List<Card> getCards() {
        return cards;
    }
}
