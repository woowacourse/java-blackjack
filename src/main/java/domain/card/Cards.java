package domain.card;

import static domain.card.Rank.SOFT_ADDITION_SCORE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Cards {

    private final List<Card> cards;

    private Cards(final Collection<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Cards emptyCards() {
        return new Cards(Collections.emptyList());
    }

    public static Cards from(final Collection<Card> cards) {
        return new Cards(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Card draw() {
        validateNotEmpty();
        return cards.remove(0);
    }

    private void validateNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다. 카드를 추가하세요.");
        }
    }

    public Card peek() {
        validateNotEmpty();
        return cards.get(0);
    }

    public void shuffle() {
        validateNotEmpty();
        Collections.shuffle(cards);
    }

    public boolean hasAce() {
        validateNotEmpty();
        return cards.stream().anyMatch(card -> card.rank() == Rank.ACE);
    }

    public int score(final boolean isSoft) {
        if (isSoft && hasAce()) {
            return sum() + SOFT_ADDITION_SCORE;
        }
        return sum();
    }

    private int sum() {
        validateNotEmpty();
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public Stream<Card> stream() {
        return cards.stream();
    }
}
