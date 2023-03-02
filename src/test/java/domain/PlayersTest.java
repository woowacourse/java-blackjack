package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PlayersTest {

    @Test
    @DisplayName("플레이어들 객체를 생성한다.")
    void createPlayersTest() {
        Assertions.assertDoesNotThrow(() -> new Players("pobi,jason"));
    }

    @Test
    @DisplayName("중복된 이름인경우 예외가 발생한다.")
    void validateDuplicatedNameTest() {
        assertThatThrownBy(() -> new Players("pobi,pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름입니다.");
    }
}
