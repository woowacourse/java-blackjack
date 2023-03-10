package domain.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BattingTest {

    @Test
    @DisplayName("음수일 경우 생성 테스트")
    public void testCreateWhenValueNegative() {
        //given
        final double value = -100;

        //when
        //then
        assertThatThrownBy(() -> {
            Batting of = Batting.of(value);
        })
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("양수일 경우 생성 테스트")
    public void testCreateWhenValuePositive() {
        //given
        final double value = 100;

        //when
        //then
        Assertions.assertDoesNotThrow(() -> Batting.of(value));
    }
}
