package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameMachineTest {

    @Test
    @DisplayName("참가자 이름을 올바르게 작성되어야 한다.")
    public void checkParticipantNames() {
        Assertions.assertThatThrownBy(() -> new GameMachine(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 참여자의 이름을 입력해주세요.");
    }
}
