package blackjack.object;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.object.gambler.Name;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningTypeDiscriminatorTest {
    @DisplayName("딜러의 합계가 21이 넘지 않는 경우 딜러의 승패를 계산 한다.")
    @CsvSource(value = {"WIN:2", "DEFEAT:2", "DRAW:1"}, delimiterString = ":")
    @ParameterizedTest
    void judgeDealerResult_DealerIsNotBust_Test(WinningType type, int expected) {
        // given
        Map<Name, Integer> gamblerScores = Map.of(
                Name.getDealerName(), 18,
                new Name("라젤"), 19, // 딜러 패배
                new Name("레오"), 21, // 딜러 패배 (플레이어 블랙잭 승리)
                new Name("비타"), 17, // 딜러 승리
                new Name("새로이"), 30, // 딜러 승리 (플레이어 버스트)
                new Name("꾹이"), 18); // 무승부
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(gamblerScores);

        // when
        Map<WinningType, Integer> result = winningDiscriminator.judgeDealerResult();

        // then
        assertThat(result.get(type)).isEqualTo(expected);
    }

    @DisplayName("딜러의 합계가 21이 넘는 경우 딜러의 승패를 계산 한다.")
    @CsvSource(value = {"WIN:2", "DEFEAT:2", "DRAW:0"}, delimiterString = ":")
    @ParameterizedTest
    void judgeDealerResult_DealerIsBust_Test(WinningType type, int expected) {
        // given
        Map<Name, Integer> gamblerScores = Map.of(
                Name.getDealerName(), 24,
                new Name("라젤"), 19, // 딜러 패배
                new Name("레오"), 21, // 딜러 패배 (플레이어 블랙잭 승리)
                new Name("비타"), 24, // 딜러 승리 (플레이어 버스트, 딜러와 점수가 같은 경우)
                new Name("새로이"), 30); // 딜러 승리 (플레이어 버스트)
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(gamblerScores);

        // when
        Map<WinningType, Integer> result = winningDiscriminator.judgeDealerResult();

        // then
        assertThat(result.get(type)).isEqualTo(expected);
    }

    @DisplayName("딜러의 합계가 21을 넘지 않는 경우 플레이어의 승패 여부를 계산한다")
    @ParameterizedTest
    @CsvSource(value = {"라젤:WIN", "레오:WIN", "비타:DEFEAT", "꾹이:DEFEAT", "새로이:DRAW"}, delimiterString = ":")
    void judgePlayerResultT_DealerIsNotBust_est(String playerName, WinningType expected) {
        // given
        Map<Name, Integer> gamblerScores = Map.of(
                Name.getDealerName(), 19,
                new Name("라젤"), 20, // 플레이어 승리
                new Name("레오"), 21, // 플레이어 블랙잭 승리
                new Name("비타"), 18, // 플레이어 패배
                new Name("꾹이"), 30, // 플레이어 버스트 패배
                new Name("새로이"), 19); // 플레이어 무승부
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(gamblerScores);

        // when
        Map<Name, WinningType> result = winningDiscriminator.judgePlayersResult();

        // then
        Name name = new Name(playerName);
        assertThat(result.get(name)).isEqualTo(expected);
    }

    @DisplayName("딜러의 합계가 21을 넘는 경우 플레이어의 승패 여부를 계산한다")
    @ParameterizedTest
    @CsvSource(value = {"라젤:WIN", "레오:WIN", "비타:DEFEAT", "꾹이:DEFEAT"}, delimiterString = ":")
    void judgePlayerResult_DealerIsBust_Test(String playerName, WinningType expected) {
        // given
        Map<Name, Integer> gamblerScores = Map.of(
                Name.getDealerName(), 24,
                new Name("라젤"), 19, // 플레이어 승리
                new Name("레오"), 21, // 플레이어 블랙잭 승리
                new Name("비타"), 24, // 플레이어 버스트 패배, 딜러와 점수가 같은 경우
                new Name("꾹이"), 30); // 플레이어 버스트 패배
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(gamblerScores);

        // when
        Map<Name, WinningType> result = winningDiscriminator.judgePlayersResult();

        // then
        Name name = new Name(playerName);
        assertThat(result.get(name)).isEqualTo(expected);
    }
}
