package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.user.exception.InvalidPlayerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {
    @Test
    @DisplayName("이름을 받아 플레이어를 생성한다")
    void createPlayerTest() {
        Name name = new Name("boxster");

        assertThat(name.getValue()).isEqualTo("boxster");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름에 공백 혹은 빈값만 들어온다면 예외를 발생시킨다")
    void createException(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(InvalidPlayerNameException.class);
    }
}
