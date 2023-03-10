package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 1000, 1_000_000, 99_999_999})
    @DisplayName("배팅금이 정상적으로 생성되어야 한다.")
    void create_success(int input) {
        // given
        Bet bet = Bet.of(input);

        // expect
        assertThat(bet.getBet())
                .isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1_000_000, 100_000_000})
    @DisplayName("배팅금이 최저 배팅금보다 낮거나 높으면 예외가 발생해야 한다.")
    void create_underMinimumOrOverMaximum(int input) {
        // expect
        assertThatIllegalArgumentException().isThrownBy(() -> {
            Bet.of(input);
        }).withMessage("[ERROR] 배팅금은 0보다 크거나 100,000,000보다 작아야 합니다.");
    }

    @ParameterizedTest
    @CsvSource({"WIN,1000", "LOSE,-1000", "DRAW,0", "BLACKJACK,1500"})
    @DisplayName("게임의 결과에 따라 배팅금이 반환되어야 한다.")
    void calculateResult_success(GameResult result, int expect) {
        // given
        Bet bet = Bet.of(1000);

        // when
        Bet resultBet = bet.calculateResult(result);

        // then
        assertThat(resultBet.getBet())
                .isEqualTo(expect);
    }
}
