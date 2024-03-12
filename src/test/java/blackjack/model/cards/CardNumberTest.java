package blackjack.model.cards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CardNumberTest {
    @DisplayName("순서로 카드 숫자를 결정한다.")
    @ParameterizedTest
    @CsvSource(value = {"0,ACE", "1,TWO", "9,TEN", "10,JACK"})
    void generate(int givenOrder, CardNumber expected) {
        CardNumber cardNumber = CardNumber.generate(givenOrder);

        assertThat(cardNumber).isEqualTo(expected);
    }

    @DisplayName("유효하지 않는 인덱스를 입력하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 14, 15})
    void validate(int givenOrder) {
        assertThatThrownBy(() -> CardNumber.generate(givenOrder))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class);
    }

    @DisplayName("카드 숫자가 Ace인지 판별한다")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "TWO,false", "THREE,false"})
    void isAce(CardNumber given, boolean expected) {
        boolean result = given.isAce();

        assertThat(result).isEqualTo(expected);
    }
}
