package blackjack.domain.participant;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("플레이어가 아무도 없을 때 예외처리")
    void validateEmptyName() {
        Assertions.assertThatThrownBy(
                () -> new Participants(new ArrayList<>())
        ).isInstanceOf(IllegalArgumentException.class);
    }


}
