package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"gr ay", " luca", " ", "", "bada "})
    @DisplayName("참가자의 이름에 빈 문자열이 들어오는 경우 예외가 발생한다.")
    void createWithBlankName(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "가나다라마바", "123456"})
    @DisplayName("참가자 이름의 길이가 5보다 큰 경우 예외가 발생한다.")
    void createWithWrongRangeName(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "gray", "luca", "hello"})
    @DisplayName("참가자 이름의 길이가 1~5 사이인 경우 정상적으로 생성된다.")
    void createPlayer(String name) {
        Player player = new Player(name);

        assertThat(player).isNotNull();
    }
}
