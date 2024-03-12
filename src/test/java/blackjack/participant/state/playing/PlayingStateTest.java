package blackjack.participant.state.playing;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import blackjack.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayingStateTest {

    @Test
    @DisplayName("플레이 상태에서 결과를 확인하려고 하면 예외를 발생시킨다.")
    void getResultOnPlayingStateTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.SPADE, Number.TEN),
                new Card(Shape.SPADE, Number.TEN)
        );
        PlayingState state = new PlayerState(new Hand(cards));
        PlayingState dealerState = new DealerState(new Hand(cards));
        // when, then
        assertThatThrownBy(() -> state.createMatchResult(dealerState))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("[ERROR] 종료 상태가 아니면 결과를 생성할 수 없습니다.");
    }
}
