package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @Nested
    @DisplayName("of는")
    class Of {

        @Test
        @DisplayName("캐싱된 카드를 반환한다.")
        void returnCachedCard() {
            Card card1 = Card.of(CardPattern.DIAMOND, CardNumber.J);
            Card card2 = Card.of(CardPattern.DIAMOND, CardNumber.J);
            assertThat(card1).isSameAs(card2);
        }
    }

    @Nested
    @DisplayName("getNumber는")
    class GetNumber {

        @ParameterizedTest()
        @CsvSource(value = {"ACE|1", "TWO|2", "TEN|10", "Q|10", "J|10", "K|10"}, delimiter = '|')
        @DisplayName("Number를 반환한다.")
        void returnNumber(CardNumber cardNumber, int input) {
            Card card = Card.of(CardPattern.DIAMOND, cardNumber);
            assertThat(card.getNumber()).isEqualTo(input);
        }
    }

    @Nested
    @DisplayName("getPattern는")
    class GetPattern {

        @ParameterizedTest
        @CsvSource(value = {"DIAMOND|다이아몬드", "CLOVER|클로버", "SPADE|스페이드", "HEART|하트"}, delimiter = '|')
        @DisplayName("Pattern을 반환한다.")
        void returnPattern(CardPattern cardPattern, String expected) {
            Card card = Card.of(cardPattern, CardNumber.ACE);
            assertThat(card.getPattern()).isEqualTo(expected);
        }
    }
}
