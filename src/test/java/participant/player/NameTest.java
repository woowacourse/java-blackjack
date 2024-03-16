package participant.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    private static final int MAX_NAME_LENGTH = 10;

    @ParameterizedTest
    @ValueSource(strings = {"hogiAndFriends", "polabearIsWhite", "hiImHogeeee"})
    @DisplayName("이름은 최대 개수를 넘으면 안된다.")
    void isOverMaxLength(String inputNames) {
        Assertions.assertThatThrownBy(() -> new Name(inputNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 " + MAX_NAME_LENGTH + "글자를 넘을 수 없습니다.");
    }

    @DisplayName("이름에 빈 값이 들어가면 안된다.")
    @Test
    void isBlankName() {
        Assertions.assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 빈값이 될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pola", "jazz", "hogi"})
    @DisplayName("이름에 담긴 값을 가져온다.")
    void getNameValue(String inputName) {
        Name name = new Name(inputName);

        Assertions.assertThat(name.getValue()).isEqualTo(inputName);
    }

    @DisplayName("이름의 앞 뒤 공백을 제거한 후 저장한다.")
    @Test
    void getTrimedName() {
        Name name = new Name("pola ");

        Assertions.assertThat(name.getValue()).isEqualTo("pola");
    }
}
