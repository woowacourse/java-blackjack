package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.result.BlackjackMatch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BustTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
            Card.from(Suit.DIAMOND, Denomination.JACK), Card.from(Suit.CLOVER, Denomination.FIVE)));
    final State state = new Bust(cards);

    @Test
    @DisplayName("버스트에서 카드를 더 받을 때 예외 발생 테스트")
    void drawIfBust() {
        assertThatThrownBy(() -> state.draw(Card.from(Suit.DIAMOND, Denomination.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("블랙잭에서 Stay 상태로 가려할 때 예외 발생 테스트")
    void drawIfStay() {
        assertThatThrownBy(state::stay)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 결과입니다.");
    }

    @Test
    @DisplayName("턴이 끝난 상태로 나타내는지 테스트")
    void isFinished() {
        assertThat(state.isFinished()).isTrue();
    }

    @Test
    @DisplayName("턴이 진행중인 상태로 나타내지 않는지 테스트")
    void isRunning() {
        assertThat(state.isRunning()).isFalse();
    }

    @Test
    @DisplayName("버스트일 경우 패배 테스트")
    void showMatch() {
        final State anotherState = new Stay(new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.THREE))));

        assertThat(state.showMatch(anotherState)).isEqualTo(BlackjackMatch.LOSE);
    }

    @Test
    @DisplayName("버스트로 진 경우 수익률 -1배 테스트")
    void profitRatio() {
        assertThat(state.profitRate(BlackjackMatch.LOSE)).isEqualTo(-1);
    }
}