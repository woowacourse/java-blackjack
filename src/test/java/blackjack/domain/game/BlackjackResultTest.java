package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackResultTest {

    @DisplayName("게임 결과에 맞는 배당 비율을 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"BLACKJACK:1.5", "WIN:1.0", "TIE:0.0", "LOSE:-1.0"}, delimiter = ':')
    void test(GameResult gameResult, double expect) {
        Player boxster = new Player("boxster");
        BlackjackResult blackjackResult = new BlackjackResult(
                Map.of(boxster, gameResult)
        );

        assertThat(blackjackResult.getRatio(boxster)).isEqualTo(expect);
    }
}
