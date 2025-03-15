package blackjack.model.state.running;

import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.state.Hand;
import blackjack.model.state.State;
import blackjack.model.state.finished.Blackjack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어의 첫 턴 테스트")
class InitialDealTest {

    @DisplayName("첫 턴 상태인 경우 카드가 2장 미만이며, 종료되지 않은 상태이다.")
    @Test
    void initialDealTest() {
        // given
        State firstTurn = new InitialDeal();

        // when
        Hand hand = firstTurn.getHand();
        int size = hand.size();

        // when, then
        assertThat(size)
                .isEqualTo(0);
        assertThat(firstTurn.isFinished())
                .isFalse();
    }

    @DisplayName("처음 생성되면 끝나지 않은 상태다.")
    @Test
    void createTest() {
        // given
        State firstTurn = new InitialDeal();

        // when
        boolean finished = firstTurn.isFinished();

        // then
        assertThat(finished)
                .isEqualTo(false);
    }

    @DisplayName("첫 턴부터 스탠드하는 경우 예외가 발생한다.")
    @Test
    void shouldNotStand() {
        // given
        State firstTurn = new InitialDeal();

        // when, then
        assertThatCode(firstTurn::stand)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("처음부터 바로 스탠드할 수 없습니다.");
    }

    @DisplayName("카드 두 장을 모두 받기 전까진 첫 턴이다.")
    @Test
    void initialDealStateTest() {
        // given
        State firstTurn = new InitialDeal();

        // when
        State state1 = firstTurn.receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(state1)
                .isInstanceOf(InitialDeal.class);
    }

    @DisplayName("카드 두 장을 모두 받은 후 블랙잭이 아닌 경우 히트가 가능한 상태이다.")
    @Test
    void hitTest() {
        // given
        State firstTurn = new InitialDeal();

        // when
        State newState = firstTurn.receiveCard(SPADE_TEN_CARD)
                .receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(newState)
                .isInstanceOf(Hit.class);
    }

    @DisplayName("카드 두 장을 모두 받은 후 블랙잭인 경우 블랙잭 상태이다.")
    @Test
    void blackjackTest() {
        // given
        State firstTurn = new InitialDeal();

        // when
        State newState = firstTurn.receiveCard(SPADE_ACE_CARD)
                .receiveCard(SPADE_TEN_CARD);

        // then
        assertThat(newState)
                .isInstanceOf(Blackjack.class);
        assertThat(newState.getHand().size())
                .isEqualTo(2);
        assertThat(newState.getHand().getTotal())
                .isEqualTo(21);
    }
}
