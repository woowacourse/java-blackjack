package domain;

import domain.model.Player;
import domain.model.PlayerBetting;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PlayerBettingTest {
    @Test
    void 플레이어_베팅_금액_저장_테스트() {
        // given
        Player phobi = Player.of("phobi");

        // when
        PlayerBetting playerBetting = PlayerBetting.of(phobi, 10_000);

        // then
        assertThat(playerBetting.getPlayer()).isEqualTo(phobi);
        assertThat(playerBetting.getValue()).isEqualTo(10_000);
    }

    @Test
    void 플레이어_베팅_금액_저장_실패_테스트() {
        // given
        Player phobi = Player.of("phobi");

        // when, then
        assertThatThrownBy(() -> {
            PlayerBetting.of(phobi, -10_000);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
