package blackjack.domain;

import java.util.LinkedHashMap;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultTest {

    @DisplayName("플레이어들의 승패를 저장하고 반환한다.")
    @Test
    void test1() {
        // given
        PlayersResult playersResult = PlayersResult.create();

        Player player1 = new Player("꾹이", null);
        Player player2 = new Player("히로", null);

        LinkedHashMap<Player, GameResultType> expect = new LinkedHashMap<>();
        expect.put(player1, GameResultType.WIN);
        expect.put(player2, GameResultType.WIN);

        // when
        playersResult.save(player1, GameResultType.WIN);
        playersResult.save(player2, GameResultType.WIN);

        // then
        assertThat(playersResult.getAllResult()).isEqualTo(expect);
    }
}
