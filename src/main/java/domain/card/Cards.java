package domain.card;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {
    private final static int MIN_SIZE = 1;
    static final String INVALID_SIZE_MESSAGE = String.format("카드 갯수가 최소 갯수인 %d보다 작습니다", MIN_SIZE);

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    static Cards of(List<Card> cards) {
        if (cards.size() < MIN_SIZE) {
            throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
        }
        return new Cards(cards);
    }

    int size() {
        return cards.size();
    }

    protected Cards add(Card card) {
        cards.add(card);
        return of(cards);
    }

    protected Cards add(Cards cards) {
        this.cards.addAll(cards.cards);
        return of(this.cards);
    }

    List<String> serialize() {
        return cards.stream().map(Card::toString).collect(Collectors.toList());
    }

    int calculateSumExceptAce() {
        Cards cards = getCardsExceptAce();
        return calculateSumExceptAce(cards);
    }

    boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    boolean hasCardNotAce() {
        return cards.stream().anyMatch(Card::isNotAce);
    }

    List<Card> getCards() {
        return cards;
    }

    private int calculateSumExceptAce(Cards cards) {
        if (cards.hasAce()) {
            throw new InvalidStateException(String.format("복수의 카드 내에 부적절한 %s가 존해합니다.", Symbol.ACE.getPattern()));
        }

        int sum = 0;
        for (Card card : cards.cards) {
            sum += card.calculateExceptAce();
        }
        return sum;
    }

    private Cards getCardsExceptAce() {
        List<Card> cards = this.cards.stream().filter(Card::isNotAce).collect(Collectors.toList());
        return Cards.of(cards);
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
