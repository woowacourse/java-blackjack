package blackjack.domain.state;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Hand;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StayTest {

    @DisplayName("stay 상태일때 수익률 계산이 정확한지 확인")
    @ParameterizedTest
    @MethodSource("calculateStayEarningRateCase")
    void calculateEarningRate(Hand playerHand, Hand dealerHand, double excepted) {
        Stay stay = new Stay(playerHand);
        State dealerState = new Stay(dealerHand);

        assertThat(stay.earningRate(dealerState)).isEqualTo(excepted);
    }

    private static Stream<Arguments> calculateStayEarningRateCase() {
        return Stream.of(
            Arguments.of(createCardHand(tenCard, sevenCard), createCardHand(tenCard, fiveCard),
                1.0),
            Arguments.of(createCardHand(tenCard, sevenCard),
                createCardHand(tenCard, fiveCard, tenCard), 1.0),
            Arguments.of(createCardHand(tenCard, sevenCard), createCardHand(tenCard, sevenCard), 0),
            Arguments.of(createCardHand(tenCard, twoCard), createCardHand(tenCard, eightCard), -1)
        );
    }

    @DisplayName("stay는 running상태가 아니다")
    @Test
    void doesNotRunningWhenStay() {
        Stay stay = new Stay(createCardHand(tenCard, sevenCard));

        assertThat(stay.isRunning()).isFalse();
    }
}
