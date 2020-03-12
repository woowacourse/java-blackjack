package blackjack.view.dto;

import blackjack.card.domain.GameResult;
import blackjack.player.domain.report.GameReport;
import blackjack.player.domain.report.GameReports;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatisticsDTOTest {

    private static Stream<Arguments> dealerResultProvider() {
        return Stream.of(
                Arguments.of(
                        new GameReports(Arrays.asList(new GameReport("bebop", GameResult.WIN))), "딜러: 0승 1패 0무"),
                Arguments.of(
                        new GameReports(Arrays.asList(new GameReport("bebop", GameResult.DRAW))), "딜러: 0승 0패 1무"),
                Arguments.of(
                        new GameReports(Arrays.asList(new GameReport("bebop", GameResult.LOSE))), "딜러: 1승 0패 0무")
        );
    }

    private static Stream<Arguments> gamblerResultProvider() {
        return Stream.of(
                Arguments.of(
                        new GameReports(Arrays.asList(new GameReport("bebop", GameResult.WIN))), "bebop: 승"),
                Arguments.of(
                        new GameReports(Arrays.asList(new GameReport("bebop", GameResult.DRAW))), "bebop: 무"),
                Arguments.of(
                        new GameReports(Arrays.asList(new GameReport("bebop", GameResult.LOSE))), "bebop: 패")
        );
    }

    @DisplayName("딜러의 승패결과는 갬블러의 승패결과에 반대로 적용된다.")
    @ParameterizedTest
    @MethodSource("dealerResultProvider")
    void getDealerResult(GameReports gameReports, String result) {
        //given
        GameStatisticsDTO gameStatisticsDTO = new GameStatisticsDTO(gameReports);

        //when
        String expect = gameStatisticsDTO.getDealerResult();

        //then
        assertThat(expect).isEqualTo(result);
    }

    @DisplayName("GameReport에 따라 이름과 승패결과를 반환한다.")
    @ParameterizedTest
    @MethodSource("gamblerResultProvider")
    void getGamblerResult(GameReports gameReports, String result) {
        //given
        GameStatisticsDTO gameStatisticsDTO = new GameStatisticsDTO(gameReports);

        //when
        String expect = gameStatisticsDTO.getGamblerResult();

        //then
        assertThat(expect).isEqualTo(result);
    }
}