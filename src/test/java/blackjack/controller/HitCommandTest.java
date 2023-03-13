package blackjack.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class HitCommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"y", "n", "Y", "N"})
    @DisplayName("알맞은 명령어가 들어왔을 때 테스트 통과")
    void of1(String command) {
        assertDoesNotThrow(() -> {
            HitCommand.of(command);
        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"r", "0"})
    @DisplayName("알맞은 명령어가 들어오지 않았을 때 예외처리")
    void of2(String command) {
        assertThatThrownBy(() -> {
            HitCommand.of(command);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
