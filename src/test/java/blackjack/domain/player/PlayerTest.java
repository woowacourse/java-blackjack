package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest(name = "[{index}] name \"{0}\"")
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어 이름이 공백일 경우 예외를 발생시킨다.")
    void checkNameBlank(String name) {
        assertThatThrownBy(() -> new Player(name) {
                    @Override
                    public boolean canTakeCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @ParameterizedTest(name = "[{index}] name {0}")
    @ValueSource(strings = {"13!", "123@", "#asd", "$wqe", "qwer%$"})
    @DisplayName("플레이어 이름에 특수문자가 들어갈 경우 예외를 발생시킨다.")
    void checkNameSpecialCharacters(String name) {
        assertThatThrownBy(() -> new Player(name) {
                    @Override
                    public boolean canTakeCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름에는 특수문자가 들어갈 수 없습니다.");
    }
}
