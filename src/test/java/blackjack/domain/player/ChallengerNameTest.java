package blackjack.domain.player;

import blackjack.domain.player.exception.InvalidPlayerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChallengerNameTest {

    @Test
    @DisplayName("도전자의 이름은 '딜러'가 될 수 없다")
    void name_is_not_same_with_dealer() {
        assertThrows(InvalidPlayerNameException.class,
                () -> new ChallengerName("딜러"));
    }
}