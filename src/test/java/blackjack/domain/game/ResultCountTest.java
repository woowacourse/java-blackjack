package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultCountTest {

    @DisplayName("인스턴스 생성 시점에 value 값은 0으로 초기화된다.")
    @Test
    void constructor_initsWithZero() {
        ResultCount resultCount = new ResultCount();

        int actual = resultCount.toInt();

        assertThat(actual).isZero();
    }

    @DisplayName("increment 메서드를 통해 값을 1씩 증가시킬 수 있다.")
    @Test
    void increment() {
        ResultCount resultCount = new ResultCount();
        for (int i = 0; i < 3; i++) {
            resultCount.increment();
        }

        int actual = resultCount.toInt();

        assertThat(actual).isEqualTo(3);
    }
}
