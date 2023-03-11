package domain.card.shuffler;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import java.util.List;

public class SequentialCardShuffler implements CardShuffler {

    @Override
    public void shuffleCards(List<Card> cards) {
        cards.clear();
        for (Suits suit : Suits.values()) {
            addCard(suit, cards);
        }
    }

    private void addCard(final Suits suit, final List<Card> cards) {
        for (Denomination denomination : Denomination.values()) {
            cards.add(Card.of(denomination, suit));
        }
    }
}
