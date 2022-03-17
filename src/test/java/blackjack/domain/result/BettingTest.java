package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThat(new Betting(1))
                .isEqualTo(new Betting(1));
    }

    @Test
    @DisplayName("생성자 오류 테스트")
    void createError() {
        assertAll(
                () -> assertThatThrownBy(() -> new Betting(-1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1 이상의 정수를 입력해주세요."),
                () -> assertThatThrownBy(() -> new Betting(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1 이상의 정수를 입력해주세요.")
        );
    }

    @Test
    @DisplayName("해시코드 테스트")
    void testHashCode() {
        assertThat(new Betting(10000).hashCode())
                .isEqualTo(10000);
    }

    @Test
    @DisplayName("배율 테스트")
    void multipleTest() {
        assertThat(new Betting(10000).getMultiple(1.5).hashCode())
                .isEqualTo(15000);
    }

    @Test
    @DisplayName("비교 테스트")
    void compareTo() {
        assertAll(
                () -> assertThat(new Betting(100).compareTo(new Betting(101))).isLessThan(0),
                () -> assertThat(new Betting(100).compareTo(new Betting(100))).isEqualTo(0),
                () -> assertThat(new Betting(100).compareTo(new Betting(99))).isGreaterThan(0)
        );
    }
}
