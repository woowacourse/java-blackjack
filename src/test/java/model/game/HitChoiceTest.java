package model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HitChoiceTest {

    @DisplayName("y 와 n 중 하나의 문자가 들어오면 객체 반환 성공")
    @ParameterizedTest
    @ValueSource(strings = {"y", "n"})
    void testValidHitChoice(String hitChoice) {
        assertThatCode(() -> HitChoice.findHitChoice(hitChoice)).doesNotThrowAnyException();
    }

    @DisplayName("y 와 n 이외의 문자가 들어오면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"예", "아니요", "Y", "N", "", "1", "2"})
    void testInvalidHitChoice(String hitChoice) {
        assertThatThrownBy(() -> HitChoice.findHitChoice(hitChoice))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("y이면 isHit true를 반환한다")
    @Test
    void isHitTrueWhenY() {
        assertThat(HitChoice.findHitChoice("y").isHit()).isTrue();
    }

    @DisplayName("n이면 isHit false를 반환한다")
    @Test
    void isHitFalseWhenN() {
        assertThat(HitChoice.findHitChoice("n").isHit()).isFalse();
    }
}
