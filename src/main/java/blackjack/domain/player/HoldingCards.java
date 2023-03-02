package blackjack.domain.player;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class HoldingCards {

    private final List<Card> cards = new ArrayList<>();

    public void initialCard(List<Card> initialCards) {
        cards.addAll(initialCards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
