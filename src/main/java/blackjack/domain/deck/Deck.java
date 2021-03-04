package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        if (cards.size() == 0) {
            throw new ArrayIndexOutOfBoundsException("[ERROR] 덱에 더이상 카드가 없습니다.");
        }
        return cards.remove(cards.size() - 1);
    }

    public List<Card> drawTwoStartCards() {
        return Stream.iterate(0, i -> i + 1)
            .map(i -> draw())
            .limit(2)
            .collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
