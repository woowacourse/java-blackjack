package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.fixture.Fixture.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    private Player player = new Player("woowahan");

    @DisplayName("딜러 이름과 동일한 이름을 입력할 경우 예외를 발생시킨다.")
    @Test
    void equals_dealer_name_exception() {
        assertThatThrownBy(() -> new Player("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러와 동일한 이름은 사용할 수 없습니다.");
    }

    @DisplayName("게임이 종료되지 않은 상태에서 수익을 구할 경우 예외가 발생하는 것을 확인한다.")
    @Test
    void earning_exception() {
        player = new Player("yukong");

        assertThatThrownBy(() -> player.earning())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 상태는 수익률을 구할 수 없습니다.");
    }

    @DisplayName("플레이어의 카드 합이 21 미만일 경우 카드를 받는 것을 확인한다.")
    @Test
    void is_drawable_true() {
        player.deal(List.of(TEN, TEN));

        assertThat(player.isDrawable()).isTrue();
    }
}
