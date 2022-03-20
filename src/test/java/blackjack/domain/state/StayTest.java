package blackjack.domain.state;

import static blackjack.domain.CardFixture.SPADE_THREE;
import static blackjack.domain.CardFixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StayTest {

    private State stay;

    @BeforeEach
    void setUp() {
        stay = Ready.dealToParticipant(SPADE_TWO, SPADE_THREE)
                .stay();
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("getProfitStayTestSet")
    @DisplayName("승패와 배팅 금액에 따라 수익 금액을 계산하여 반환한다.")
    void profit(Outcome outcome, int money, int result) {
        Profit profit = stay.profit(outcome, new BetMoney(money));

        assertThat(profit.get()).isEqualTo(result);
    }

    private static Stream<Arguments> getProfitStayTestSet() {
        int money = 100;
        return Stream.of(
                Arguments.of(Outcome.WIN, money, money),
                Arguments.of(Outcome.DRAW, money, 0),
                Arguments.of(Outcome.LOSE, money, money * -1)
        );
    }

    @Test
    @DisplayName("stay일 때 draw를 하면 에러가 발생한다.")
    void draw() {
        assertThatThrownBy(() -> stay.draw(SPADE_THREE))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("stay일 때 stay를 하면 에러가 발생한다.")
    void stay() {
        assertThatThrownBy(stay::stay)
                .isInstanceOf(IllegalStateException.class);
    }
}
