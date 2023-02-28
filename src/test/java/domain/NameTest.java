package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {
    @Test
    @DisplayName("이름길이가 5자를 초과하는 경우 예외가 발생한다.")
    void validateNameLengthTest() {
        assertThatThrownBy(() -> new Name("pobipobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름길이는 5자를 초과 할 수 없습니다.");

    }

    @ParameterizedTest
    @DisplayName("이름이 공백일 수 없다.")
    @NullAndEmptySource
    void validateNameBlank(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(Exception.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }
}
