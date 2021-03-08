package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final String NO_REMAIN_CARD_ERROR_MESSAGE = "남은 카드가 없습니다.";
    public static final int TOP_CARD = 0;

    private final List<Card> cards;

    public Cards() {
        this(Collections.emptyList());
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card draw() {
        if (cards.size() == 0) {
            throw new IndexOutOfBoundsException(NO_REMAIN_CARD_ERROR_MESSAGE);
        }
        return cards.remove(TOP_CARD);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::score)
                .sum();
        long aceCount = cards.stream()
                .filter(Card::isAce)
                .count();
        return Denomination.plusRemainAceScore(score, aceCount);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
