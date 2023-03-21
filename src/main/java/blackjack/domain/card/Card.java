package blackjack.domain.card;

import blackjack.domain.game.Score;

import java.util.*;

public class Card {

    private static final Map<String, Card> cards = new LinkedHashMap<>();

    /**
     * todo: 질문1.
     * static 생성자를 이용할 때, 현재 방식처럼 이미 존재하는 ArrayList에 값을 넣어주는 것이 맞나요?
     * 아니면, static 생성자 내부에서 List타입을 생성해 필드에 List자체를 넣어주는 것이 맞나요?
     *
     * 완태님의 의견이 궁금합니다!
     */
    static {
        for (Shape shape : Shape.values()) {
            for (Denomination denomination : Denomination.values()) {
                cards.put(toKey(shape, denomination), new Card(shape, denomination));
            }
        }
    }

    private final Shape shape;
    private final Denomination denomination;

    private Card(Shape shape, Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;

    }

    private static String toKey(Shape shape, Denomination denomination) {
        return shape.name() + denomination.name();
    }

    public static Card from(Shape shape, Denomination denomination) {
        return cards.computeIfAbsent(toKey(shape, denomination), ignored -> new Card(shape, denomination));
    }

    public static List<Card> getCards() {
        return new ArrayList<>(cards.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, denomination);
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", denomination=" + denomination +
                '}';
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Shape getShape() {
        return shape;
    }

    public Score getScore() {
        return new Score(denomination.getScore());
    }

}
