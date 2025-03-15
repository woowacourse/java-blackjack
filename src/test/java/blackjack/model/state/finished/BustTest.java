package blackjack.model.state.finished;

import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.running.InitialDeal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("버스트 테스트")
class BustTest {

    @DisplayName("버스트인 경우 카드가 3장 이상이며, 총합이 21을 초과한다.")
    @Test
    void bustTest() {
        // given
        State bustState = createBustState();

        // when
        Hand hand = bustState.getHand();
        int total = hand.getTotal();
        int size = hand.size();

        // then
        assertThat(total)
                .isGreaterThan(21);
        assertThat(size)
                .isGreaterThanOrEqualTo(3);
    }

    @DisplayName("버스트 상태는 카드를 받을 수 없다.")
    @Test
    void receiveCardTest() {
        // given
        State bustState = createBustState();

        // when, then
        assertThat(bustState)
                .isInstanceOf(Bust.class);
        assertThatCode(() -> bustState.receiveCard(SPADE_TWO_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("버스트되어 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("버스트 상태는 스탠드할 수 없다.")
    @Test
    void standTest() {
        // given
        State bustState = createBustState();

        // when, then
        assertThat(bustState)
                .isInstanceOf(Bust.class);
        assertThatCode(bustState::stand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("버스트되어 스탠드할 수 없습니다.");
    }

    @DisplayName("버스트 상태는 끝난 상태이다.")
    @Test
    void isFinishedTest() {
        // given
        State bustState = createBustState();

        // when
        boolean isFinished = bustState.isFinished();

        // then
        assertThat(isFinished)
                .isTrue();
    }

    @DisplayName("버스트 상태는 블랙잭이 아니다.")
    @Test
    void isBlackjackTest() {
        // given
        State state = createBustState();
        if (!(state instanceof Bust bustState)) {
            throw new IllegalArgumentException("버스트 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        boolean isBlackjack = bustState.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isFalse();
    }

    @DisplayName("버스트 상태는 버스트이다.")
    @Test
    void isBustTest() {
        // given
        State state = createBustState();
        if (!(state instanceof Bust bustState)) {
            throw new IllegalArgumentException("버스트 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        boolean isBust = bustState.isBust();

        // then
        assertThat(isBust)
                .isTrue();
    }

    @DisplayName("버스트 상태의 배당률은 0이다.")
    @Test
    void earningsRateTest() {
        // given
        State state = createBustState();
        if (!(state instanceof Bust bustState)) {
            throw new IllegalArgumentException("버스트 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        double earningsRate = bustState.earningsRate();

        // then
        assertThat(earningsRate)
                .isZero();
    }

    private State createBustState() {
        State firstTurn = new InitialDeal();
        return firstTurn.receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_TWO_CARD);
    }
}
