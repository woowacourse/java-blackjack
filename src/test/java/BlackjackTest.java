import blackjack.domain.Player;
import blackjack.domain.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BlackjackTest {

    @Test
    @DisplayName("플레이어의 목록으로 객체를 생성한다")
    void make_players() {
        //given
        List<String> playerNames = List.of("두리", "비타");
        List<Player> playerExpected = List.of(new Player("두리"), new Player("비타"));

        //when
        Players players = new Players(playerNames);

        //then
        Assertions.assertThat(players.getPlayers()).isEqualTo(playerExpected);
    }
}
