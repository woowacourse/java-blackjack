package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class NameTest {

    @Test
    @DisplayName("이름이 잘 생성된다.")
    void nameConstructSuccessTest() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new Name("이름"));
    }

    @ParameterizedTest
    @DisplayName("이름이 없을 때 예외가 발생한다.")
    @NullAndEmptySource
    void noNameExceptionTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
