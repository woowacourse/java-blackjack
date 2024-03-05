package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardNumberTest {
    @DisplayName("순서로 카드 숫자를 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,ACE", "2,TWO", "10,TEN", "11,JACK"})
    void generate(int givenOrder, CardNumber expected) {
        CardNumber cardNumber = CardNumber.generate(givenOrder);

        assertThat(cardNumber).isEqualTo(expected);
    }
}
