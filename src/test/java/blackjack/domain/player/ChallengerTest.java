package blackjack.domain.player;

import static org.junit.jupiter.api.Assertions.assertThrows;

import blackjack.domain.player.exception.InvalidPlayerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChallengerTest {

    @Test
    @DisplayName("이름이 '딜러'인 경우 예외가 발생한다")
    void validate_name() {
        assertThrows(InvalidPlayerNameException.class,
                () -> new Challenger("딜러"));
    }
}
