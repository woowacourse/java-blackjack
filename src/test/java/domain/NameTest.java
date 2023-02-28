package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NameTest {
    @Test
    @DisplayName("이름길이가 5자를 초과하는 경우 예외가 발생한다.")
    void validateNameLengthTest() {
        Assertions.assertThatThrownBy(() -> new Name("pobipobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름길이는 5자를 초과 할 수 없습니다.");

    }
}
