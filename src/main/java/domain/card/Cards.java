package domain.card;

import java.util.*;
import java.util.stream.Stream;

public class Cards {

    private final Stack<Card> cards;

    public Cards() {
        this(new Stack<>());
    }

    public Cards(final Collection<Card> cards) {
        this.cards = new Stack<>();
        this.cards.addAll(cards);
    }

    public Cards(final Cards cards) {
        this(cards.cards);
    }

    public static Cards deck() {
        List<Card> cards = Arrays.stream(CardShape.values())
                                 .flatMap(Cards::totalCardsOf)
                                 .toList();
        return new Cards(cards);
    }

    private static Stream<Card> totalCardsOf(final CardShape cardShape) {
        return EnumSet.allOf(CardNumber.class)
                      .stream()
                      .map(cardNumber -> new Card(cardShape, cardNumber));
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public Card draw() {
        return cards.pop();
    }

    public Card peek() {
        return cards.peek();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int sum() {
        return cards.stream()
                    .mapToInt(Card::value)
                    .sum();
    }

    public boolean hasAce() {
        return cards.stream()
                    .anyMatch(card -> card.isAce());
    }

    public List<Card> toList() {
        return List.copyOf(cards);
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
