package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardNumberTest {

    @ParameterizedTest(name = "input : {0}")
    @CsvSource(value = {"A:ACE", "2:TWO", "3:THREE", "4:FOUR", "5:FIVE", "6:SIX", "7:SEVEN", "8:EIGHT", "9:NINE", "K:KING", "Q:QUEEN", "J:JACK"}, delimiter = ':')
    @DisplayName("of()는 카드넘버를 문자로 받아 값을 반환해 준다.")
    void get_value_of_card_number_test(String number, String value) {
        // given & when
        CardNumber expected = CardNumber.valueOf(value);

        // then
        Assertions.assertThat(CardNumber.of(number)).isEqualTo(expected);
    }
}
