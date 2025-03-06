package blackjack.domain;

import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gambler.Name;
import blackjack.view.WinningType;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningTypeDiscriminatorTest {
    @DisplayName("최종 승패의 결과를 계산한다.")
    @Test
    void judgeDealerResultTest() {
        // given
        Map<Name, Integer> playerScores = Map.of(new Name("라젤"), 19, new Name("레오"), 21, new Name("비타"), 30);
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(20, playerScores);

        // when
        Map<WinningType, Integer> result = winningDiscriminator.judgeDealerResult();

        // then
        assertThat(result.get(WIN)).isEqualTo(2);
        assertThat(result.get(DEFEAT)).isEqualTo(1);
        assertThat(result.get(DRAW)).isEqualTo(0);
    }

    @DisplayName("")
    @ParameterizedTest
    @CsvSource(value = {"라젤:DEFEAT", "레오:WIN", "비타:DEFEAT", "꾹이:DRAW"}, delimiterString = ":")
    void judgePlayerResultTest(String playerName, WinningType expected) {
        // given
        Map<Name, Integer> playerScores = Map.of(new Name("라젤"), 19,
                new Name("레오"), 21,
                new Name("비타"), 30,
                new Name("꾹이"), 20);
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(20, playerScores);

        // when
        Map<Name, WinningType> result = winningDiscriminator.judgePlayersResult();

        // then
        assertThat(result.get(new Name(playerName))).isEqualTo(expected);
    }
}