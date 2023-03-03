package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"gr ay", " luca", " ", "", "bada "})
    @DisplayName("이름에 빈 문자열이 들어오는 경우 예외가 발생한다.")
    void createWithBlankName(String inputName) {
        assertThatThrownBy(() -> new Name(inputName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "가나다라마바", "123456"})
    @DisplayName("이름의 길이가 5보다 큰 경우 예외가 발생한다.")
    void createWithWrongRangeName(String inputName) {
        assertThatThrownBy(() -> new Name(inputName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "gray", "luca", "hello"})
    @DisplayName("이름의 길이가 1~5 사이인 경우 정상적으로 생성된다.")
    void createPlayer(String inputName) {
        Name name = new Name(inputName);

        assertThat(name).isNotNull();
    }
}
