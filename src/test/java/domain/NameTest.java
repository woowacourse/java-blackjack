package domain;

import domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NameTest {

    @Test
    @DisplayName("플레이어 이름 문자열을 입력하면 Name 객체가 정상적으로 생성된다")
    void generateName() {
        String name = "roy";

        assertDoesNotThrow(() -> new Name(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {" roy", "r oy", "roy "})
    @DisplayName("플레이어 이름에 공백이 포함될 경우 예외가 발생한다.")
    void exceptionWhenNameContainsBlank(String name) {

        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름에 공백은 포함할 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어 이름이 6자를 초과하면 예외가 발생한다")
    void exceptionWithMoreThan5NameLetters() {
        String name = "royroyroy";

        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 1자 이상, 6자 이하만 가능합니다.");
    }

    @Test
    @DisplayName("이름이 1자 미만일 경우 예외가 발생한다")
    void exceptionWithLessThan1NameLetters() {
        String name = "";

        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 1자 이상, 6자 이하만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"딜러", "dealer", "Dealer", "DEALER"})
    @DisplayName("플레이어의 이름이 '딜러'일 경우 예외가 발생한다.")
    void exceptionWhenPlayerNameIsDealer(String name) {

        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름으로 '딜러(Dealer)'는 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 '딜러'인 Name 객체가 정상적으로 생성된다.")
    void generateDealerName() {

        assertDoesNotThrow(() -> Name.generateDealerName());
    }
}
