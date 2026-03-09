package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    void 플레이어가_0명이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 1명 이상이어야 합니다.");
    }

    @Test
    void 중복된_이름의_플레이어가_있으면_예외가_발생한다() {
        List<Player> duplicated = List.of(new Player("pobi"), new Player("pobi"));

        assertThatThrownBy(() -> new Players(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름의 플레이어가 있습니다.");
    }

    @Test
    void 플레이어가_1명이면_생성에_성공한다() {
        List<Player> players = List.of(new Player("pobi"));

        Players result = new Players(players);

        assertThat(result.getPlayers()).hasSize(1);
    }
}
