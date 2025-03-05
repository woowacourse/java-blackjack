import blackjack.domain.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BlackjackTest {

    @Test
    @DisplayName("플레이어의 목록으로 객체를 생성한다")
    void make_players() {
        List<String> playerNames = List.of("두리", "비타");
        Players players = new Players(playerNames);
        Assertions.assertThat(players.getPlayers()).contains(new Player("두리"), new Player("비타"));
    }
}
