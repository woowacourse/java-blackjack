package domain.card;

import java.util.List;
import java.util.Objects;

public class Cards {
    private final List<Card> cards;
    private final static int MIN_SIZE = 1;
    static final String INVALID_SIZE_MESSAGE = String.format("카드 갯수가 최소 갯수인 %d보다 작습니다", MIN_SIZE);

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(List<Card> cards) {
        if (cards.size() < MIN_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
        }
        return new Cards(cards);
    }

    //todo: 접근제한자 확인하기
    public List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    protected Cards add(Card card) {
        cards.add(card);
        return of(cards);
    }

    protected Cards add(Cards cards) {
        this.cards.addAll(cards.getCards());
        return of(this.cards);
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
