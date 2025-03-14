package domain;

import static org.assertj.core.api.Assertions.*;
import static util.ExceptionConstants.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardNumberTypeTest {
    @DisplayName("인덱스에 알맞은 카드 숫자를 반환한다")
    @Test
    void test1() {
        //given
        int two = 2;

        //when
        CardNumberType cardNumberTypeByTwo = CardNumberType.findByRandomIndex(two);

        //then
        assertThat(cardNumberTypeByTwo.ordinal()).isEqualTo(2);
    }

    @DisplayName("만약 존재하지 않는 인덱스인 경우 예외가 발생한다")
    @Test
    void test2() {
        //given
        int invalidIndex = 15;

        //when & then
        assertThatThrownBy(() -> CardNumberType.findByRandomIndex(invalidIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_HEADER + "해당하는 카드 숫자의 인덱스가 존재하지 않습니다.");
    }
}
