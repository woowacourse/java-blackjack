package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {

    /**
     * todo: 질문1.
     * static 생성자를 이용할 때, 현재 방식처럼 이미 존재하는 ArrayList에 값을 넣어주는 것이 맞나요?
     * 아니면, static 생성자 내부에서 List타입을 생성해 필드에 List자체를 넣어주는 것이 맞나요?
     * 완태님의 의견이 궁금합니다!
     */

    private final Shape shape;
    private final Denomination denomination;
    private static final List<Card> cards = new ArrayList<>();

    static {
        Arrays.stream(Shape.values())
                .forEach(shape -> Arrays.stream(Denomination.values())
                        .forEach(denomination -> cards.add(new Card(shape, denomination))
                        )
                );
    }

    private Card(Shape shape, Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;

    }

    public static Card from(Shape shape, Denomination denomination) {
        if (hasCardOf(shape, denomination)) return findCardOf(shape, denomination);
        return new Card(shape, denomination);
    }

    private static boolean hasCardOf(Shape shape, Denomination denomination) {
        return cards.stream()
                .anyMatch(card -> card.shape == shape && card.denomination == denomination);
    }

    private static Card findCardOf(Shape shape, Denomination denomination) {
        return cards.stream()
                .filter(card -> card.shape == shape && card.denomination == denomination)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 정보와 일치하는 카드가 존재하지 않습니다"));
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Shape getShape() {
        return shape;
    }

    public int getScore() {
        return denomination.getScore();
    }

}
