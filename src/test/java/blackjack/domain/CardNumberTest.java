package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
    @DisplayName("카드 숫자는 1부터 13사이의 숫자이다")
    void validCardNUmberTest(int value) {
        assertDoesNotThrow(()->{
            CardNumber.of(value);
        });
    }

    @DisplayName("카드 숫자가 1부터 13 범위를 넘어가면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 14})
    void invalidCardNumberTest(int value) {
        assertThatThrownBy(() -> CardNumber.of(value));
    }

    @Test
    @DisplayName("CardNumber는 Comparable을 구현한 비교가능한 객체이다.")
    void cardNumberComparableTest() {
        final CardNumber targetNumber = CardNumber.of(2);

        final CardNumber smallNumber = CardNumber.of(1);
        final CardNumber sameNumber = CardNumber.of(2);
        final CardNumber largerNumber = CardNumber.of(3);

        assertAll(
                ()-> assertThat(targetNumber.compareTo(smallNumber)).isGreaterThanOrEqualTo(1),
                ()-> assertThat(targetNumber.compareTo(sameNumber)).isEqualTo(0),
                ()-> assertThat(targetNumber.compareTo(largerNumber)).isLessThanOrEqualTo(-1)
        );
    }

    @Test
    @DisplayName("같은 값을 같는 CardNumber는 같은 주소값을 갖는 객체이다.")
    void cardNumberTest() {
        final CardNumber number1 = CardNumber.of(1);
        final CardNumber number2 = CardNumber.of(1);

        assertThat(number1 == number2).isTrue();
    }
}
