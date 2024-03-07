package domain.blackjack;

import domain.card.Card;
import domain.card.CardName;
import domain.card.CardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardPointCalculatorTest {
    static Stream<Arguments> calculateParameter() {
        return Stream.of(Arrays.stream(CardType.values())
                .map(CardPointCalculatorTest::makeSameTypeCards)
                .flatMap(Collection::stream)
                .map(card -> Arguments.of(card, makeCardPoint(card.name())))
                .toArray(Arguments[]::new));
    }

    private static List<Card> makeSameTypeCards(CardType cardType) {
        return Arrays.stream(CardName.values())
                .map(cardName -> new Card(cardName, cardType))
                .toList();
    }

    private static CardPoint makeCardPoint(CardName cardName) {
        CardPoint cardPoint = new CardPoint(cardName.getCardNumber());
        if (cardName.getCardNumber() > 10) {
            return new CardPoint(10);
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
