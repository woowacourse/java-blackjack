package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    @DisplayName("생성")
    void of() {
        assertThatCode(() -> Score.of(1)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 실패 - 음수인 경우")
    void of1() {
        assertThatThrownBy(() -> Score.of(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("더하기")
    void add() {
        assertThat(Score.of(1).add(2)).isEqualTo(Score.of(3));
    }

    @Test
    @DisplayName("빼기")
    void subtract() {
        assertThat(Score.of(10).subtract(10)).isEqualTo(Score.of(0));
    }

    @Test
    @DisplayName("블랙잭 확인")
    void isBlackJack() {
        assertThat(Score.of(21).isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("버스트 확인")
    void isBurst() {
        assertThat(Score.of(22).isBurst()).isTrue();
    }

    @Test
    @DisplayName("점수비교 - 큰 경우")
    void isHigherThan() {
        assertThat(Score.of(20).isHigherThan(Score.of(19))).isTrue();
    }

    @Test
    @DisplayName("점수비교 - 동등")
    void isSameAs() {
        assertThat(Score.of(22).isSameAs(Score.of(22))).isTrue();
    }

}
