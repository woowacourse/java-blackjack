package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Deck {
    public static final int NUMBER_OF_FIRST_DEAL_CARDS = 2;

    private List<Card> deck;

    public Deck(List<Card> deck) {
        validateDuplicatedCards(deck);
        this.deck = deck;
    }

    private void validateDuplicatedCards(List<Card> deck) {
        if (new HashSet<Card>(deck).size() < deck.size()) {
            throw new IllegalArgumentException("덱에 중복된 카드가 존재할 수 없습니다.");
        }
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Card deal() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("덱에서 나누어주지 않은 카드가 한장도 없습니다.");
        }
        return this.deck.remove(0);
    }

    public List<Card> dealFirstCards() {
        List<Card> firstDealtCards = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_FIRST_DEAL_CARDS; i++) {
            firstDealtCards.add(this.deal());
        }
        return Collections.unmodifiableList(firstDealtCards);
    }
}
