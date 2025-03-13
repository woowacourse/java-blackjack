package domain;

import static domain.MatchResult.DRAW;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Hand;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatchResultTest {
    @ParameterizedTest
    @DisplayName("결과 산출 테스트")
    @MethodSource("provideSumForCalculateWinner")
    void calculateWinnerTest(int dealerSum, int playerSum, MatchResult matchResult) {
        assertThat(MatchResult.calculateWinner(dealerSum, playerSum)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideSumForCalculateWinner() {
        return Stream.of(Arguments.of(
                10,20,WIN,
                20,20,DRAW,
                20,10,LOSE,
                20,25,LOSE
        ));
    }

    @Test
    @DisplayName("플레이어가 21 점을 넘어서 패배 처리 테스트")
    void calculateWinnerTestPlayerOver21() {
        assertThat(MatchResult.calculateWinner(20, 24)).isEqualTo(LOSE);
    }

    @Test
    @DisplayName("무승부 테스트")
    void calculateDrawTest() {
        assertThat(MatchResult.calculateWinner(17, 17)).isEqualTo(DRAW);
    }

    @Test
    @DisplayName("딜러 결과 산출 테스트")
    void calculateDealerResultTest() {
        Map<Player, MatchResult> playerResult = new HashMap<>();
        playerResult.put(new Player(new Hand(new ArrayList<>()), "pobi"), WIN);
        playerResult.put(new Player(new Hand(new ArrayList<>()), "lisa"), DRAW);
        playerResult.put(new Player(new Hand(new ArrayList<>()), "neo"), LOSE);

        Map<MatchResult, Integer> dealerResult = MatchResult.calculateDealerResult(playerResult);

        assertThat(dealerResult.get(WIN)).isEqualTo(1);
        assertThat(dealerResult.get(DRAW)).isEqualTo(1);
        assertThat(dealerResult.get(LOSE)).isEqualTo(1);
    }
}
