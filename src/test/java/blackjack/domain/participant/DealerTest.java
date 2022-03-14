package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardPattern.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    @Nested
    @DisplayName("딜러가 카드 한 장을 더 받을 수 있는지 확인할 수 있다.")
    class IsEnd {

        @Test
        @DisplayName("헌 장을 더 받을 수 있다.")
        void isNotEnd() {
            final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SIX))));
            assertTrue(dealer.canDraw());
        }

        @Test
        @DisplayName("헌 장을 더 받을 수 없다.")
        void isEnd() {
            final Dealer dealer = new Dealer(
                    new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN))));
            assertFalse(dealer.canDraw());
        }
    }

    @ParameterizedTest
    @DisplayName("딜러가 버스트인지 반환한다.")
    @MethodSource("provideNewCardAndExpectedBust")
    void isBust(final Card card, final boolean expected) {
        final Dealer dealer = new Dealer(new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SIX))));
        dealer.draw(card);
        assertThat(dealer.isBust()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNewCardAndExpectedBust() {
        return Stream.of(
                Arguments.of(Card.of(SPADE, KING), true),
                Arguments.of(Card.of(SPADE, THREE), false)
        );
    }

    @Test
    @DisplayName("종료되지 않은 딜러가 모든 카드를 반환하려고 하는 경우 예외가 발생해야 한다.")
    void getCardsException() {
        final Dealer dealer = new Dealer(
                new ArrayList<>(Arrays.asList(Card.of(SPADE, TEN), Card.of(SPADE, SIX))));

        assertThatThrownBy(dealer::getCards)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
    }
}
