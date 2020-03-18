package domain.card;

import java.util.List;
import java.util.Objects;

public class PlayingCards extends Cards {
    private static final int BLACK_JACK = 21;
    private static final int MIN_SIZE = 2;
    private final Cards cards;

    private PlayingCards(List<Card> cards) {
        super(cards);
        this.cards = Cards.of(cards);
    }

    private PlayingCards(Cards cards) {
        super(cards.getCards());
        this.cards = cards;
    }

    public static PlayingCards of(List<Card> cards) {
        if (cards.size() < MIN_SIZE) {
            throw new IllegalArgumentException(String.format("카드의 개수가 최소 갯수인 %s보다 작습니다.", MIN_SIZE));
        }

        return new PlayingCards(cards);
    }

    public PlayingCards add(Card card) {
        return new PlayingCards(cards.add(card));
    }

    public PlayingCards add(Cards cardsToAdd) {
        return new PlayingCards(cards.add(cardsToAdd));
    }

    public int calculate() {
        int sumWithoutAce = cards.calculateSumExceptAce();
        return calculateSumWithAces(sumWithoutAce);
    }

    public boolean isBust() {
        return BLACK_JACK < calculate();
    }

    public boolean isNotBust() {
        return calculate() <= BLACK_JACK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayingCards that = (PlayingCards) o;
        return Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
