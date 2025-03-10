package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gambler.Name;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningTypeDiscriminatorTest {
    @DisplayName("딜러의 최종 승패의 결과를 계산한다.")
    @CsvSource(value = {"WIN:2", "DEFEAT:1", "DRAW:0"}, delimiterString = ":")
    @ParameterizedTest
    void judgeDealerResultTest(WinningType type, int expected) {
        // given
        Map<Name, Integer> playerScores = Map.of(new Name("라젤"), 19, new Name("레오"), 21, new Name("비타"), 30);
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(20, playerScores);

        // when
        Map<WinningType, Integer> result = winningDiscriminator.judgeDealerResult();

        // then
        assertThat(result.get(type)).isEqualTo(expected);
    }

    @DisplayName("플레이어의 승패 여부를 계산한다")
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
        Name name = new Name(playerName);
        assertThat(result.get(name)).isEqualTo(expected);
    }
}
