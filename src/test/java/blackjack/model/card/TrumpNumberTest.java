package blackjack.model.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TrumpNumberTest {

    @ParameterizedTest(name = "{0}에 8을 더하면 {1}이다")
    @CsvSource(value = {"2:10", "7:15", "25:33"}, delimiterString = ":")
    void sumTo_value_EIGHT(int value, int expected) {
        assertThat(TrumpNumber.EIGHT.sumTo(value)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}에 ACE을 더하면 {1}이다")
    @CsvSource(value = {"2:3", "7:8", "25:26"}, delimiterString = ":")
    void sumTo_value_ACE(int value, int expected) {
        assertThat(TrumpNumber.ACE.sumTo(value)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}에 8을 더하면 {1}이다")
    @CsvSource(value = {"2:12", "7:17", "25:35"}, delimiterString = ":")
    void sumTo_value(int value, int expected) {
        assertThat(TrumpNumber.KING.sumTo(value)).isEqualTo(expected);
    }
}
