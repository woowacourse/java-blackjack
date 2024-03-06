package domain;

import java.util.List;
import java.util.Scanner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputView;

class PlayerTest {
    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        Assertions.assertThatCode(() -> new Player("pobi", Hands.createEmptyPacket()))
                .doesNotThrowAnyException();
    }

    @DisplayName("null이나 공백을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    @NullSource
    void nullOrBlankInputThrowException(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name, Hands.createEmptyPacket()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
