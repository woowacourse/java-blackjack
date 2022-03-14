package blackjack.domain.card;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBust;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfNonBlackjackTwentyOne;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfTwenty;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CardHandleStateTest {

    @DisplayName("of 메서드는 카드 패를 토대로 해당되는 CardHandState를 반환한다.")
    @Nested
    class OfTest {

        @DisplayName("21 이하의 패는 CAN_HIT을 반환한다.")
        @Test
        void of_scoreUnder21ReturnsCanHit() {
            CardHandState actual = CardHandState.ofPlayer(getCardBundleOfTwenty());

            assertThat(actual).isEqualTo(CardHandState.CAN_HIT);
        }

        @DisplayName("블랙잭이 아닌 21의 패는 STAY를 반환한다.")
        @Test
        void of_scoreOfNonBlackjack21ReturnsMaxScore() {
            CardHandState actual = CardHandState.ofPlayer(getCardBundleOfNonBlackjackTwentyOne());

            assertThat(actual).isEqualTo(CardHandState.STAY);
        }

        @DisplayName("블랙잭인 패는 BLACKJACK을 반환한다.")
        @Test
        void of_blackjackReturnsBlackjack() {
            CardHandState actual = CardHandState.ofPlayer(getCardBundleOfBlackjack());

            assertThat(actual).isEqualTo(CardHandState.BLACKJACK);
        }

        @DisplayName("21을 초과하는 패는 BUST를 반환한다.")
        @Test
        void of_scoreOver21ReturnsBust() {
            CardHandState actual = CardHandState.ofPlayer(getCardBundleOfBust());

            assertThat(actual).isEqualTo(CardHandState.BUST);
        }
    }

    @DisplayName("CAN_HIT 이외의 상태에서 isFinished 메서드를 호출하면 true를 반환한다.")
    @ParameterizedTest(name = "[{index}] {0} : false")
    @ValueSource(strings = {"STAY", "BLACKJACK", "BUST"})
    void isFinished_returnFalseIfNotCanHit(String finishedState) {
        boolean actual = CardHandState.valueOf(finishedState)
                .isFinished();

        assertThat(actual).isTrue();
    }

    @DisplayName("STAY에서 isStay 메서드를 호출하면 true를 반환한다.")
    @Test
    void isStay() {
        boolean actual = CardHandState.STAY.isStay();

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
