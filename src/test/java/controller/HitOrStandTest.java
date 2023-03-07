package controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.HitOrStand;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class HitOrStandTest {
    @Nested
    class 반환 {
        @ParameterizedTest
        @ValueSource(strings = {"HIT", "", " "})
        void should_예외를던진다_when_입력이정해진문자열이아닌경우(String rawInput) {
            //given

            //when
            ThrowingCallable throwingCallable = () -> HitOrStand.from(rawInput);

            //then
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("y나 n이어야 합니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"y:HIT", "n:STAND"}, delimiter = ':')
        void should_정상생성_when_입력이y나n일경우(String decision, HitOrStand expected) {
            //given

            //when
            HitOrStand actual =  HitOrStand.from(decision);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}