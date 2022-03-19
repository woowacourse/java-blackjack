package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;

class CardsTest {

    @ParameterizedTest(name = "{index}: {4}")
    @MethodSource("invalidParameters")
    @DisplayName("버스트인지 확인한다.")
    void isBust(CardNumber number1,
                CardNumber number2,
                CardNumber number3,
                boolean result,
                String testName) {
        Card heart = new Card(CardShape.HEART, number1);
        Card spade = new Card(CardShape.SPADE, number2);
        Card club = new Card(CardShape.CLUB, number3);
        
        CardGroup playerCardGroup = new CardGroup();
        playerCardGroup.addCard(heart);
        playerCardGroup.addCard(spade);
        playerCardGroup.addCard(club);

        boolean isBust = playerCardGroup.isBust();

        assertThat(isBust).isEqualTo(result);
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
                Arguments.of(CardNumber.FOUR, CardNumber.Q, CardNumber.SIX, false, "숫자 합이 20이면 false를 반환해야 합니다."),
                Arguments.of(CardNumber.J, CardNumber.SEVEN, CardNumber.FOUR, false, "숫자 합이 21이면 false를 반환해야 합니다."),
                Arguments.of(CardNumber.J, CardNumber.Q, CardNumber.TWO, true, "숫자 합이 22이면 true를 반환해야 합니다.")
        );
    }
}
