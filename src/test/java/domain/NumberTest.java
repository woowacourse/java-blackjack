package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberTest {

    @DisplayName("인덱스로 카드 번호를 찾아 반환한다.")
    @Test
    void findNumberByIndex() {
        // given
        int kingIndex = 11;
        Number expected = Number.KING;

        // when
        Number number = Number.findBy(kingIndex);

        // then
        assertThat(number).isEqualTo(expected);
    }
}
