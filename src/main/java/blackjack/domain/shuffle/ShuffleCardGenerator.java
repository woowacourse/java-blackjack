package blackjack.domain.shuffle;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ShuffleCardGenerator implements CardGenerator {

    private final List<Card> deck = shuffleDeck();

    @Override
    public Card pickCard() {
        try {
            return deck.removeFirst();
        } catch (NoSuchElementException | UnsupportedOperationException exception) {
            throw new IllegalStateException("[ERROR] 카드가 더이상 없습니다.");
        }
    }

    private List<Card> shuffleDeck() {
        final List<Card> cards = new ArrayList<>(DECK);
        Collections.shuffle(cards);
        return cards;
    }
}
