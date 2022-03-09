package blackjack.domain;

import static blackjack.domain.CardNumber.A;
import static blackjack.domain.CardNumber.SEVEN;
import static blackjack.domain.CardNumber.SIX;
import static blackjack.domain.CardNumber.TEN;
import static blackjack.domain.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드가 17이상일 때 카드를 추가하면 예외가 발생해야 한다.")
    void drawExceptionByLimitDealerScore() {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN))));
        assertThatThrownBy(() -> dealer.draw(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[Error] 딜러 카드가 이미 17이상입니다.");
    }

    @Nested
    @DisplayName("딜러가 카드 한 장을 더 받을 수 있는지 확인할 수 있다.")
    class IsEnd {

        @Test
        @DisplayName("헌 장을 더 받을 수 있다.")
        void isNotEnd() {
            final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SIX))));
            assertFalse(dealer.isEnd());
        }

        @Test
        @DisplayName("헌 장을 더 받을 수 없다.")
        void isEnd() {
            final Dealer dealer = new Dealer(
                    new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN))));
            assertTrue(dealer.isEnd());
        }
    }

    @Test
    @DisplayName("종료되지 않은 딜러가 모든 카드를 반환하려고 하는 경우 예외가 발생해야 한다.")
    void getCardsException() {
        final Dealer dealer = new Dealer(
                new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN))));

        assertThatThrownBy(() -> dealer.getCards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
    }
}
