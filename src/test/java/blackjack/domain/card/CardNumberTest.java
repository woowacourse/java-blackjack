package blackjack.domain.card;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardNumberTest {

    @Nested
    @DisplayName("getOptimizeTotalNumber는")
    class getOptimizeTotalNumber {

        @ParameterizedTest
        @CsvSource(value = {"TEN|NINE|TWO|21", "ACE|FIVE|FIVE|21",
            "ACE|SIX|FIVE|12", "ACE|ACE|NINE|21", "TEN|TEN|TWO|22"},
            delimiter = '|')
        @DisplayName("카드의 숫자가 주어지면 최적화된 총합을 알려준다.")
        void it_returns_optimize_total_number(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3,
            int expected) {
            List<CardNumber> cardNumberList = List.of(cardNumber1, cardNumber2, cardNumber3);
            Assertions.assertThat(CardNumber.getOptimizeTotalNumber(cardNumberList)).isEqualTo(expected);
        }
    }
}
