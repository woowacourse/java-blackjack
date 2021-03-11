package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private static final int EMPTY_SIZE = 0;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        if (cards.size() == EMPTY_SIZE) {
            throw new ArrayIndexOutOfBoundsException("[ERROR] 덱에 더이상 카드가 없습니다.");
        }
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
