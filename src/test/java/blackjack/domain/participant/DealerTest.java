package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardPattern.SPADE;
import static blackjack.testutil.CardFixtureGenerator.createCards;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import blackjack.domain.card.Card;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러의 카드가 17이상일 때 카드를 추가하면 예외가 발생해야 한다.")
    void drawExceptionByLimitDealerScore() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN));
        final Participant dealer = Dealer.createNewDealer(cards);
        assertThatThrownBy(() -> dealer.draw(Card.of(SPADE, A)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 턴이 종료되어 카드를 더 받을 수 없습니다.");
    }

    @Nested
    @DisplayName("딜러가 카드 한 장을 더 받을 수 있는지 확인할 수 있다.")
    class CanDraw {

        @Test
        @DisplayName("헌 장을 더 받을 수 있다.")
        void isNotEnd() {
            final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SIX));
            final Participant dealer = Dealer.createNewDealer(cards);
            assertTrue(dealer.canDraw());
        }

        @Test
        @DisplayName("헌 장을 더 받을 수 없다.")
        void isEnd() {
            final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SEVEN));
            final Participant dealer = Dealer.createNewDealer(cards);
            assertFalse(dealer.canDraw());
        }
    }

    @Test
    @DisplayName("종료되지 않은 딜러가 모든 카드를 반환하려고 하는 경우 예외가 발생해야 한다.")
    void getCardsException() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, SIX));
        final Dealer dealer = Dealer.createNewDealer(cards);

        assertThatThrownBy(() -> dealer.cards())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
    }

    @Test
    @DisplayName("턴이 종료되지 않았는데 스코어를 반환하려고 하면 예외를 발생시킨다.")
    void calculateResultScoreExceptionByNotEndTurn() {
        final List<Card> cards = createCards(Card.of(SPADE, TEN), Card.of(SPADE, TWO));
        final Dealer dealer = Dealer.createNewDealer(cards);

        assertThatThrownBy(() -> dealer.calculateResultScore())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
    }
}
