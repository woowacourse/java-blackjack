package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    @DisplayName("생성 시 null카드가 들어올 경우 예외를 발생시킨다.")
    void createExceptionByNullCards() {
        assertThatThrownBy(() -> new Running(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("cards는 null이 들어올 수 없습니다.");
    }
}
