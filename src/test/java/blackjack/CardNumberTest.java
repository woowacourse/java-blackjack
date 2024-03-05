package blackjack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.CardNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {
    @DisplayName("순서로 카드 숫자를 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,ACE", "2,TWO", "10,TEN", "11,JACK"})
    void generate(int givenOrder, CardNumber expected) {
        CardNumber cardNumber = CardNumber.generate(givenOrder);

        assertThat(cardNumber).isEqualTo(expected);
    }

    @DisplayName("유효하지 않은 숫자를 입력하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 14, 15})
    void validate(int givenOrder) {
        assertThatThrownBy(() -> CardNumber.generate(givenOrder))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드 숫자가 Ace인지 판별한다")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "TWO,false", "THREE,false"})
    void isAce(CardNumber given, boolean expected) {
        boolean result = given.isAce();

        assertThat(result).isEqualTo(expected);
    }
}
