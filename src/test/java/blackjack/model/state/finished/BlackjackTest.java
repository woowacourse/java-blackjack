package blackjack.model.state.finished;

import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.MatchResult;
import blackjack.model.state.State;
import blackjack.model.state.running.InitialDeal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("블랙잭 테스트")
class BlackjackTest {

    @DisplayName("블랙잭 상태인 경우 두 장의 카드의 합이 21이다.")
    @Test
    void blackjackTest() {
        // given
        State blackjackState = createBlackjackState();

        // when
        int total = blackjackState.getTotal();
        int size = blackjackState.getHandCards().size();

        // then
        assertThat(total)
                .isEqualTo(21);
        assertThat(size)
                .isEqualTo(2);
    }

    @DisplayName("블랙잭 상태는 카드를 받을 수 없다.")
    @Test
    void receiveCardTest() {
        // given
        State blackjackState = createBlackjackState();

        // when, then
        assertThat(blackjackState)
                .isInstanceOf(Blackjack.class);
        assertThatCode(() -> blackjackState.receiveCard(SPADE_TWO_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭이라 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("블랙잭 상태는 스탠드할 수 없다.")
    @Test
    void standTest() {
        // given
        State blackjack = createBlackjackState();

        // when, then
        assertThat(blackjack)
                .isInstanceOf(Blackjack.class);
        assertThatCode(blackjack::stand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭이라 스탠드할 수 없습니다.");
    }

    @DisplayName("블랙잭 상태는 끝난 상태이다.")
    @Test
    void isFinishedTest() {
        // given
        State blackjackState = createBlackjackState();

        // when
        boolean isFinished = blackjackState.isFinished();

        // then
        assertThat(isFinished)
                .isTrue();
    }

    @DisplayName("블랙잭 상태는 블랙잭이다.")
    @Test
    void isBlackjackTest() {
        // given
        State blackjack = createBlackjackState();
        if (!(blackjack instanceof Blackjack blackjackState)) {
            throw new IllegalArgumentException("블랙잭 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        boolean isBlackjack = blackjackState.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isTrue();
    }

    @DisplayName("블랙잭 상태는 버스트될 수 없다.")
    @Test
    void bustTest() {
        // given
        State blackjack = createBlackjackState();
        if (!(blackjack instanceof Blackjack blackjackState)) {
            throw new IllegalArgumentException("블랙잭 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        boolean isBust = blackjackState.isBust();

        // then
        assertThat(isBust)
                .isFalse();
        assertThatCode(() -> blackjack.receiveCard(SPADE_TEN_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭이라 카드를 더 받을 수 없습니다.");
    }

    private State createBlackjackState() {
        State firstTurn = new InitialDeal();
        return firstTurn.receiveCard(SPADE_ACE_CARD)
                .receiveCard(SPADE_TEN_CARD);
    }
}
