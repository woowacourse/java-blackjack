package blackjack.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BlackjackGame;
import blackjack.domain.player.PlayerName;
import blackjack.exception.NeedRetryException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("'딜러' 이름으로 블랙잭 게임을 할 수 없다.")
    @Test
    void validateDealName() {
        final List<PlayerName> names = List.of(new PlayerName("딜러"), new PlayerName("kirby"));

        assertThatThrownBy(() -> new BlackjackGame(names))
                .isInstanceOf(NeedRetryException.class)
                .hasMessage("딜러를 이름으로 사용할 수 없습니다.");
    }
}
