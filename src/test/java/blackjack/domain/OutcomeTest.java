package blackjack.domain;

import blackjack.domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {

    @DisplayName("버스트일 때 승패 결과 테스트가 정상적으로 출력되는 경우")
    @ParameterizedTest
    @CsvSource(value = {"22:22:WIN", "23:17:LOSE", "18:23:WIN"}, delimiter = ':')
    void outcome_when_buster_test(int dealerScore, int playerScore, String result) {
        assertThat(Outcome.findOutcome(new Score(dealerScore), new Score(playerScore)))
                .isEqualTo(Outcome.valueOf(result));
    }

    @DisplayName("버스트가 아닌 경우 승패 결과 테스트가 정상적으로 출력되는 경우")
    @ParameterizedTest
    @CsvSource(value = {"19:17:WIN", "23:17:LOSE", "18:23:WIN"}, delimiter = ':')
    void outcome_test(int dealerScore, int playerScore, String result) {
        assertThat(Outcome.findOutcome(new Score(dealerScore), new Score(playerScore)))
                .isEqualTo(Outcome.valueOf(result));
    }

    @DisplayName("승->패, 패->승, 무->무와 같이 reverse되는 결과값이 제대로 나오는 지 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN:LOSE", "LOSE:WIN", "DRAW:DRAW"}, delimiter = ':')
    void reverse_test(String result, String reverse) {
        assertThat(Outcome.valueOf(result).reverse()).isEqualTo(Outcome.valueOf(reverse));
    }
}