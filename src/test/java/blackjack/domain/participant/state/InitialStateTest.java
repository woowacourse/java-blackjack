package blackjack.domain.participant.state;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;

class InitialStateTest {

    @DisplayName("게임을 시작하기 위해서는 2장의 카드가 준비되어야 한다.")
    @Test
    void cardSizeExceptionTest() {
        assertThatThrownBy(() -> InitialState.initiallyDrawCards(SPADE_TEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("처음에 배분받는 카드는 2장이어야 합니다.");
    }

    @DisplayName("처음 2장의 카드 합계가 21이면 Blackjack 상태로 변해야 한다.")
    @Test
    void isBlackjackStateTest() {
        final State state = InitialState.initiallyDrawCards(SPADE_ACE, SPADE_KING);
        assertThat(state).isInstanceOf(BlackjackState.class);
    }

    @DisplayName("처음 2장의 카드 합계가 21이 아니면 Hit 상태로 변해야 한다.")
    @Test
    void isHitStateTest() {
        final State state = InitialState.initiallyDrawCards(SPADE_ACE, SPADE_TWO);
        assertThat(state).isInstanceOf(HitState.class);
    }

    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @Test
    void getScoreTest() {
        final State state = InitialState.initiallyDrawCards(SPADE_ACE, SPADE_TWO);
        assertThat(state.getScore()).isEqualTo(13);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @Test
    void getCardsTest() {
        final State state = InitialState.initiallyDrawCards(SPADE_ACE, SPADE_TWO);
        final List<Card> actualCards = state.getCards();
        assertThat(actualCards).isEqualTo(List.of(SPADE_ACE, SPADE_TWO));
    }

}
