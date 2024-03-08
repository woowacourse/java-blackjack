package domain;

import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {
    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        Assertions.assertThatCode(() -> new Player("pobi", Hands.createEmptyPacket()))
                .doesNotThrowAnyException();
    }

    @DisplayName("공백을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void BlankInputThrowException(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name, Hands.createEmptyPacket()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참여자 이름에 공백을 입력할 수 없습니다.");
    }

    @DisplayName("null을 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullSource
    void nullInputThrowException(String name) {
        Assertions.assertThatThrownBy(() -> new Player(name, Hands.createEmptyPacket()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 참여자 이름입니다.");
    }
}
