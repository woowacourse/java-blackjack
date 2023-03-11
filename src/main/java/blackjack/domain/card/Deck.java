package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public final class Deck {

    private final Stack<Card> deck = new Stack<>();

    public Deck() {
        init();
    }

    private void init() {
        for (Denomination denomination : Denomination.values()) {
            fillCards(denomination);
        }
        Collections.shuffle(deck);
    }

    private void fillCards(final Denomination denomination) {
        for (Suit suit : Suit.values()) {
            deck.add(new Card(denomination, suit));
        }
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("현재 남아있는 카드가 존재하지 않습니다.");
        }
        return deck.pop();
    }

    public List<Card> drawTwoCard() {
        return new ArrayList<>(List.of(drawCard(), drawCard()));
    }

    public List<Card> getDeck() {
        return List.copyOf(deck);
    }
}
