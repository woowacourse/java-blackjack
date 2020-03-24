package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.exceptions.InvalidPlayerException;

public class NameTest {
    @DisplayName("이름이 제대로 생성되는지 생성자 테스트")
    @NullAndEmptySource
    @ParameterizedTest
    void nameConstructorTest(String input) {
        assertThatThrownBy(() -> {
            new Name(input);
        }).isInstanceOf(InvalidPlayerException.class);
    }
}
