package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {

    @DisplayName("이름은 1글자 ~ 5글자 사이인 경우 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"s", "sadfi"})
    void createNameSuccess(String input) {
        Name name = new Name(input);

        assertThat(name.getName()).isEqualTo(input);
    }

    @DisplayName("이름은 1글자 ~ 5글자 사이가 아닌 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "skkdfs"})
    void createNameFail(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 1 ~ 5글자 사이여야 합니다.");
    }

}
