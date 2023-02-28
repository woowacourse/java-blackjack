package blackjack.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13})
    @DisplayName("카드 숫자는 1부터 13사이의 숫자이다")
    void validCardNUmberTest(int value) {
        assertDoesNotThrow(()->{
            new CardNumber(value);
        });
    }

    @DisplayName("카드 숫자가 1부터 13 범위를 넘어가면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 14})
    void invalidCardNumberTest(int value) {
        assertThatThrownBy(() -> new CardNumber(value));
    }
}
