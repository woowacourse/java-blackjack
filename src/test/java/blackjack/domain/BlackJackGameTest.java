package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    @DisplayName("blackjack 생성 시 이름이 null이 들어오는 경우 예외를 발생시킨다.")
    void initExceptionByNull() {
        assertThatThrownBy(() -> BlackJackGame.init(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("blackjackgame은 null이 들어올 수 없습니다.");
    }
}