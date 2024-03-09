package domain.blackjack;

import domain.card.Card;
import domain.card.CardName;
import domain.card.CardType;
import java.util.Arrays;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardPointCalculatorTest {
    private static final int MAX_NUMBER = 10;

    static Stream<Arguments> calculateParameter() {
        return Arrays.stream(CardType.values())
                .flatMap(cardType -> Arrays.stream(CardName.values())
                        .map(cardName -> {
                            Card card = new Card(cardName, cardType);
                            CardPoint cardPoint = makeCardPoint(cardName);
                            return Arguments.of(card, cardPoint);
                        }));
    }

    private static CardPoint makeCardPoint(CardName cardName) {
        CardPoint cardPoint = new CardPoint(cardName.getCardNumber());
        if (cardName.getCardNumber() > MAX_NUMBER) {
            return new CardPoint(MAX_NUMBER);
        }
        return cardPoint;
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
