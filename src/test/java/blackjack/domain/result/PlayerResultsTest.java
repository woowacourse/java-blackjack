package blackjack.domain.result;

import static blackjack.domain.result.GameResult.PLAYER_LOSE;
import static blackjack.domain.result.GameResult.PLAYER_WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어 결과 도메인 테스트")
class PlayerResultsTest {

    @DisplayName("이름으로 결과를 조회할 수 있다")
    @Test
    void testFindGameResultByName() {
        PlayerResult playerResult1 = new PlayerResult("리비", PLAYER_WIN);
        PlayerResult playerResult2 = new PlayerResult("리비", PLAYER_LOSE);
        PlayerResults playerResults = new PlayerResults(List.of(playerResult1, playerResult2));

        assertThat(playerResults.findResultByName("리비")).isEqualTo(PLAYER_WIN);
    }
}
