package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsTest {

    @Nested
    @DisplayName("getTotalNumber는")
    class GetTotalNumber {

        @Test
        @DisplayName("가지고 있는 카드의 총합을 반환한다.")
        void returnTotalNumber() {
            Cards cards = new Cards(
                List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK), Card.of(CardPattern.HEART, CardNumber.EIGHT)));

            Assertions.assertThat(cards.getTotalNumber()).isEqualTo(18);
        }

        @ParameterizedTest
        @CsvSource(value = {"TWO|THREE|FIVE|21", "TEN|FIVE|FIVE|21", "ACE|ACE|EIGHT|21", "TEN|TEN|ACE|22"},
            delimiter = '|')
        @DisplayName("ACE가 포함된 경우 21 넘지 않는 최대한 가까운 총합을 반환한다.")
        void returnTotalNumberWithAce(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3,
            int expected) {
            Cards cards = new Cards(
                List.of(Card.of(CardPattern.CLOVER, CardNumber.ACE), Card.of(CardPattern.HEART, cardNumber1),
                    Card.of(CardPattern.SPADE, cardNumber2), Card.of(CardPattern.DIAMOND, cardNumber3)));

            Assertions.assertThat(cards.getTotalNumber()).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("add는")
    class Add {

        @Test
        @DisplayName("카드를 추가한다.")
        void addCard() {
            Cards cards = new Cards(
                List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK), Card.of(CardPattern.HEART, CardNumber.EIGHT)));
            cards.add(Card.of(CardPattern.CLOVER, CardNumber.FOUR));

            Assertions.assertThat(cards.getTotalNumber()).isEqualTo(22);
        }
    }
}
