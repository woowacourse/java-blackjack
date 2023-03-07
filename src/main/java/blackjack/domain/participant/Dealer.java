package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    public Card getFirstCard() {
        final List<Card> cards = new ArrayList<>(super.getCards());
        return cards.get(0);
    }
}
