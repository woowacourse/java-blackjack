package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.exceptions.InvalidPlayerException;

public class PlayerTest {

    @DisplayName("Player 생성자 예외 처리 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void playerInvalidConstructorTest(String value) {
        assertThatThrownBy(() -> {
            new Player(value);
        }).isInstanceOf(InvalidPlayerException.class);
    }

    @DisplayName("Player 생성자 테스트")
    @Test
    void playerValidConstructorTest() {
        assertThat(new Player("bingbong")).isNotNull();
    }
}
