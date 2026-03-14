package domain.state;

import domain.member.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StayTest {

    @DisplayName("Stay 상태는 종료된 상태이다")
    @Test
    void isFinished_Always_ReturnTrue() {
        State state = new Stay(new Hand());
        assertThat(state.isFinished()).isTrue();
    }

    @DisplayName("Stay 상태의 기본 수익률은 1.0이다")
    @Test
    void stayEarningRate_Always_ReturnOnePointZero() {
        State state = new Stay(new Hand());
        assertThat(state.earningRate()).isEqualTo(1.0);
    }

    @DisplayName("Stay 상태에서 카드를 더 뽑으려 하면 예외가 발생한다")
    @Test
    void draw_WhenCalled_ThrowsException() {
        State state = new Stay(new Hand());
        assertThatThrownBy(() -> state.draw(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
