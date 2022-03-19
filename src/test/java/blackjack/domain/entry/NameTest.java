package blackjack.domain.entry;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import blackjack.domain.entry.vo.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

class NameTest {

    @ParameterizedTest
    @EmptySource
    @DisplayName("플레이어의 이름이 딜러인 경우 예외를 발생한다.")
    void throwExceptionContainsBlackName(String name) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Name(name))
            .withMessage("플레이어의 이름은 공백이 될 수 없습니다.");
    }

}
