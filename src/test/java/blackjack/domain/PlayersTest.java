package blackjack.domain;

import blackjack.domain.player.Players;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("참가자의 이름이 중복인 경우 확인")
    void duplicateTest() {
        Assertions.assertThatThrownBy(() -> Players.fromNames(List.of("a", "a"), (p) -> HitFlag.Y))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자 이름은 중복될 수 없습니다.");
    }
}
