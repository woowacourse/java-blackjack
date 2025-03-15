package blackjack.model.state.finished;

import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.running.InitialDeal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("스탠드 테스트")
class StandTest {

    @DisplayName("스탠드인 경우 카드는 2장 이상이며, 21 미만이다.")
    @Test
    void standTest() {
        // given
        State stand = create16HitState();
        if (!(stand.stand() instanceof Stand standState)) {
            throw new IllegalArgumentException("스탠드 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        int total = standState.getTotal();
        int size = standState.getHandCards().size();

        // then
        assertThat(total)
                .isLessThan(21);
        assertThat(size)
                .isGreaterThanOrEqualTo(2);
    }

    @DisplayName("스탠드 상태는 카드를 받을 수 없다.")
    @Test
    void receiveCardTest() {
        // given
        State stand = create16HitState();
        State standState = stand.stand();

        // when, then
        assertThat(standState)
                .isInstanceOf(Stand.class);
        assertThatCode(() -> standState.receiveCard(SPADE_TWO_CARD))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 스탠드하여 카드를 받을 수 없습니다.");
    }

    @DisplayName("스탠드 상태는 스탠드할 수 없다.")
    @Test
    void standNotTest() {
        // given
        State stand = create16HitState();
        State standState = stand.stand();

        // when, then
        assertThat(standState)
                .isInstanceOf(Stand.class);
        assertThatCode(standState::stand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 스탠드했습니다.");
    }

    @DisplayName("스탠드 상태는 끝난 상태이다.")
    @Test
    void isFinishedTest() {
        // given
        State stand = create16HitState();
        State standState = stand.stand();

        // when
        boolean isFinished = standState.isFinished();

        // then
        assertThat(isFinished)
                .isTrue();
    }

    @DisplayName("스탠드 상태는 블랙잭이 아니다.")
    @Test
    void isBlackjackTest() {
        // given
        State stand = create16HitState();
        if (!(stand.stand() instanceof Stand standState)) {
            throw new IllegalArgumentException("스탠드 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        boolean isBlackjack = standState.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isFalse();
    }

    @DisplayName("스탠드 상태는 버스트될 수 없다.")
    @Test
    void isBustTest() {
        // given
        State stand = create16HitState();
        if (!(stand.stand() instanceof Stand standState)) {
            throw new IllegalArgumentException("스탠드 상태가 아닌 경우 테스트할 수 없습니다.");
        }

        // when
        boolean isBust = standState.isBust();

        // then
        assertThat(isBust)
                .isFalse();
    }

    private State create16HitState() {
        State firstTurn = new InitialDeal();
        return firstTurn.receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_SIX_CARD);
    }
}
