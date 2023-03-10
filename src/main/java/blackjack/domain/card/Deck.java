package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Deck {

    private static final int INITIAL_CAPACITY = 52;

    private final Set<Card> deck = new HashSet<>(INITIAL_CAPACITY);

    public Deck() {
        init();
    }

    private void init() {
        for (Denomination denomination : Denomination.values()) {
            fillCards(denomination);
        }
    }

    private void fillCards(final Denomination denomination) {
        for (Suit suit : Suit.values()) {
            deck.add(new Card(denomination, suit));
        }
    }

    public Card drawCard() {
        Card drawCard = deck.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("현재 남아있는 카드가 존재하지 않습니다."));
        deck.remove(drawCard);

        return drawCard;
    }

    public List<Card> drawTwoCard() {
        return new ArrayList<>(List.of(drawCard(), drawCard()));
    }

    public Set<Card> getDeck() {
        return Collections.unmodifiableSet(deck);
    }
}
