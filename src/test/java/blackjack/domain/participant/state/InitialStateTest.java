package blackjack.domain.participant.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;

class InitialStateTest {

    @DisplayName("게임을 시작하기 위해서는 2장의 카드가 준비되어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.InitialStateTestProvider#provideForCardSizeExceptionTest")
    void cardSizeExceptionTest(final List<Card> cards) {
        assertThatThrownBy(() -> InitialState.initiallyDrawCards(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("처음에 배분받는 카드는 2장이어야 합니다.");
    }

    @DisplayName("처음 2장의 카드 합계가 21이면 Blackjack 상태로 변해야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.InitialStateTestProvider#provideForIsBlackjackStateTest")
    void isBlackjackStateTest(final List<Card> cards) {
        final State state = InitialState.initiallyDrawCards(cards);
        assertThat(state).isInstanceOf(BlackjackState.class);
    }

    @DisplayName("처음 2장의 카드 합계가 21이 아니면 Hit 상태로 변해야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.state.provider.InitialStateTestProvider#provideForIsHitStateTest")
    void isHitStateTest(final List<Card> cards) {
        final State state = InitialState.initiallyDrawCards(cards);
        assertThat(state).isInstanceOf(HitState.class);
    }

}
