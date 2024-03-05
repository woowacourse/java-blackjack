package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardPointCalculatorTest {

    //ToDo 메서드 분리 완전 필요
    static Stream<Arguments> calculateParameter() {
        List<Arguments> arguments = new ArrayList<>();
        for (CardType cardType : CardType.values()) {
            for (CardName cardName : CardName.values()) {
                Card card = new Card(cardName, cardType);
                CardPoint cardPoint;
                if (cardName.getCardNumber() > 10) {
                    cardPoint = new CardPoint(10);
                } else {
                    cardPoint = new CardPoint(cardName.getCardNumber());
                }
                arguments.add(Arguments.of(card, cardPoint));
            }
        }
        return Stream.of(arguments.toArray(new Arguments[0]));
    }

    @ParameterizedTest
    @MethodSource("calculateParameter")
    @DisplayName("카드 점수가 제대로 변환되는지 검증")
    void calculate(Card card, CardPoint expected) {
        CardPoint cardPoint = CardPointCalculator.calculate(card);
        Assertions.assertThat(cardPoint)
                .isEqualTo(expected);
    }
}
