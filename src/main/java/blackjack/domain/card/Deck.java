package blackjack.domain.card;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public final class Deck {

    private final Deque<Card> deck;

    public Deck() {
        List<Card> cards = init();
        deck = new ConcurrentLinkedDeque<>(cards);
    }

    private List<Card> init() {
        List<Card> cards = new ArrayList<>();
        for (Denomination denomination : Denomination.values()) {
            fillCards(denomination, cards);
        }
        shuffle(cards);

        return cards;
    }

    private void fillCards(final Denomination denomination, final List<Card> cards) {
        for (Suit suit : Suit.values()) {
            cards.add(new Card(denomination, suit));
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
