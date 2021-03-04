package blackjack.domain;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중복 검증")
    void validateDuplication() {
        assertThatThrownBy(() -> Players.valueOf("a,b,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("승패 결과")
    void match() {
        Players players = new Players(Arrays.asList(TestSetUp.WINNER, TestSetUp.LOSER, TestSetUp.TIE_PLAYER));
        GameResult gameResult = players.match(TestSetUp.DEALER);

        Map<Player, ResultType> expected = new HashMap<>();
        expected.put(TestSetUp.WINNER, ResultType.WIN);
        expected.put(TestSetUp.TIE_PLAYER, ResultType.TIE);
        expected.put(TestSetUp.LOSER, ResultType.LOSE);

        assertThat(gameResult).isEqualTo(new GameResult(expected));
    }
}
