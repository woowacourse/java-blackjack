package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardDeck implements CardStorage {
    private final List<Card> cards;

    public CardDeck() {
        this.cards = new ArrayList<>();
    }

    public int getDeckSize() {
        return cards.size();
    }

    @Override
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public Set<Integer> calculatePossibleSums() {
        List<Integer> sums = new ArrayList<>();
        sums.add(0);

        for (Card card : cards) {
            sums = sums.stream()
                    .flatMap(sum -> card.checkScore().stream().map(value -> sum + value))
                    .toList();
        }
        return new HashSet<>(sums);
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }
}
