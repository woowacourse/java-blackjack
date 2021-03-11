package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Cards;
import blackjack.domain.card.PlayerCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {
    @DisplayName("stay로 상태변환 테스트")
    @Test
    void stay() {
        Running running = new Hit(new PlayerCards());
        assertThat(running.stay()).isInstanceOf(Stay.class);
    }

    @DisplayName("카드뽑기 테스트")
    @Test
    void draw() {
        Running running = new Hit(new PlayerCards());
        assertThatCode(() -> running.draw(Cards.getInstance().draw()))
            .doesNotThrowAnyException();
    }

    @DisplayName("종료확인 테스트")
    @Test
    void isFinished() {
        Running running = new Hit(new PlayerCards());
        assertThat(running.isFinished()).isFalse();
    }
}