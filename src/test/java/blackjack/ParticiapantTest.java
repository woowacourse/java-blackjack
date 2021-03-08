package blackjack;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticiapantTest {
    @Test
    @DisplayName("이름의 빈값을 잘 검증하는지 확인")
    void validateEmptyName() {
        assertThatThrownBy(() -> new Player("")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Participant.EMPTY_NAME_ERROR);
    }
}
