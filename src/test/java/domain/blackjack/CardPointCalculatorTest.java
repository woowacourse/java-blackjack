package domain.blackjack;

import static domain.card.CardName.TEN;

import domain.card.Card;
import domain.card.CardName;
import domain.card.CardType;
import domain.card.Deck;
import domain.card.FirstCardSelectStrategy;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CardPointCalculatorTest {
    static Stream<Arguments> calculateParameter() {
        Deck deck = Deck.fullDeck();
        int totalCardCount = CardType.values().length * CardName.values().length;
        return IntStream.range(0, totalCardCount)
                .mapToObj(value -> deck.draw(new FirstCardSelectStrategy()))
                .map(card -> Arguments.of(card, makeCardPoint(card.cardName())));
    }

    private static CardPoint makeCardPoint(CardName cardName) {
        CardPoint cardPoint = new CardPoint(cardName.getCardNumber());
        if (cardName.getCardNumber() > TEN.getCardNumber()) {
            return new CardPoint(TEN.getCardNumber());
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
