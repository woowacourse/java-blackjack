package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.PlayStatus;

class PlayerTest {

    @Test
    @DisplayName("상태를 STAY로 변경한다.")
    void stay() {
        // give
        Player player = new Player(Name.of("pobi"));

        // when
        player.stay();
        PlayStatus actual = player.getStatus();

        // then
        assertThat(actual).isEqualTo(PlayStatus.STAY);
    }
}
