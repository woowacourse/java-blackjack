package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.betting.BettingMoney;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어가 0명이면 예외가 발생한다")
    void constructor_throwsException_whenNoPlayers() {
        // given & when & then
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 1명 이상이어야 합니다.");
    }

    @Test
    @DisplayName("중복된 이름의 플레이어가 있으면 예외가 발생한다")
    void constructor_throwsException_whenDuplicateNames() {
        // given
        List<Player> duplicated = List.of(
                new Player("pobi", new BettingMoney(1000)),
                new Player("pobi", new BettingMoney(1000))
        );

        // when & then
        assertThatThrownBy(() -> new Players(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름의 플레이어가 있습니다.");
    }

    @Test
    @DisplayName("플레이어가 1명이면 생성에 성공한다")
    void constructor_succeeds_whenOnePlayer() {
        // given
        List<Player> players = List.of(new Player("pobi", new BettingMoney(1000)));

        // when
        Players result = new Players(players);

        // then
        assertThat(result.getPlayers()).hasSize(1);
    }
}
