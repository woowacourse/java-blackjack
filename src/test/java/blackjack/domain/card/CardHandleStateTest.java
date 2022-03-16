package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardHandleStateTest {

    @DisplayName("CAN_HIT 이외의 상태에서 isFinished 메서드를 호출하면 true를 반환한다.")
    @ParameterizedTest(name = "[{index}] {0} : false")
    @ValueSource(strings = {"STAY", "BLACKJACK", "BUST"})
    void isFinished_returnFalseIfNotCanHit(String finishedState) {
        boolean actual = CardHandState.valueOf(finishedState)
                .isFinished();

        assertThat(actual).isTrue();
    }

    @DisplayName("BLACKJACK에서 isBlackjack 메서드를 호출하면 true를 반환한다.")
    @Test
    void isBlackjack() {
        boolean actual = CardHandState.BLACKJACK.isBlackjack();

        assertThat(actual).isTrue();
    }

    @DisplayName("BUST에서 isBust 메서드를 호출하면 true를 반환한다.")
    @Test
    void isBust() {
        boolean actual = CardHandState.BUST.isBust();

        assertThat(actual).isTrue();
    }
}
