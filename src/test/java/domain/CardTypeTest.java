package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static util.ExceptionConstants.ERROR_HEADER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTypeTest {
    @DisplayName("인덱스에 알맞은 카드 모양을 반환한다")
    @Test
    void test1() {
        //given
        int two = 2;

        //when
        CardType cardTypeByTwo = CardType.findByRandomIndex(two);

        //then
        assertThat(cardTypeByTwo.ordinal()).isEqualTo(2);
    }

    @DisplayName("만약 존재하지 않는 인덱스인 경우 예외가 발생한다")
    @Test
    void test2() {
        //given
        int invalidIndex = 15;

        //when & then
        assertThatThrownBy(() -> CardType.findByRandomIndex(invalidIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ERROR_HEADER + "해당하는 카드 모양의 인덱스가 존재하지 않습니다.");
    }
}
