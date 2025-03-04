package blackjack.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("이름들로 Player 객체를 생성한다.")
    @Test
    void createPlayers() {
        // given
        List<Player> players = List.of(new Player("엠제이"), new Player("밍트"));

        // when & then
        Assertions.assertThatCode(() -> new Players(players))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복된 이름의 경우 예외를 발생한다.")
    @Test
    void createDuplicatePlayers() {
        // given
        List<Player> players = List.of(new Player("엠제이"), new Player("엠제이"));

        // when & then
        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 중복된 이름을 입력했습니다.");
    }
}
