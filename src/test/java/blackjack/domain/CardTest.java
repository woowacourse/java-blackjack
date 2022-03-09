package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardTest {

    @Test
    @DisplayName("범위 밖의 숫자로 생성시 예외를 발생시킨다.")
    void exceptionNumberOutOfRange() {
        assertThatThrownBy(() -> new Card(0, CardType.DIAMOND))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 올바른 카드 번호가 아닙니다.");
    }
}
