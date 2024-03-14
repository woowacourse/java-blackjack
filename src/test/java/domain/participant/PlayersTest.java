package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.BettingMoney;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("플레이어의 수가 최소 1명 최대 8명으로 이루어져 있지 않은 경우 예외가 발생한다.")
    @Test
    void validateSize() {
        List<Player> players = List.of();
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최소 1명 최대 8명입니다 : 현재 %d명".formatted(0));
    }

    @DisplayName("중복된 플레이어가 존재하는 경우 예외가 발생한다.")
    @Test
    void validateNotDuplicate() {
        List<Player> players = List.of(new Player(new Name("tobi"), new BettingMoney(5000)), new Player(new Name("tobi"), new BettingMoney(5000)));
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 가진 플레이어는 존재할 수 없습니다.");
    }
}
