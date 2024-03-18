package blackjack.model.result;

import blackjack.model.player.Name;
import blackjack.view.dto.PlayerEarning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BettingBoardTest {
    @ParameterizedTest(name = "{0} 1000 -> {1}")
    @MethodSource("provideMatchResultAndExpectedEarning")
    @DisplayName("플레이어의 게임 결과로 수익을 구한다")
    void determineEarningTest(MatchResult matchResult, int expectedEarning) {
        // given
        Name playerName = new Name("mia");
        BettingMoney bettingMoney = new BettingMoney(1000);
        BettingBoard bettingBoard = new BettingBoard(List.of(playerName), List.of(bettingMoney));

        // when
        PlayerEarning playerEarning = bettingBoard.determineEarning(playerName, matchResult);

        // then
        assertThat(playerEarning.earning()).isEqualTo(expectedEarning);
    }

    private static Stream<Arguments> provideMatchResultAndExpectedEarning() {
        return Stream.of(
                Arguments.of(MatchResult.WIN, 1000),
                Arguments.of(MatchResult.LOSE, -1000),
                Arguments.of(MatchResult.PUSH, 0),
                Arguments.of(MatchResult.BLACKJACK, 1500)
        );
    }
}
