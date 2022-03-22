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

class BlackjackTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
            Card.from(Suit.DIAMOND, Denomination.ACE)));
    private final State state = new Blackjack(cards);

    @Test
    @DisplayName("블랙잭에서 카드를 더 받을 때 예외 발생 테스트")
    void drawIfBlackjack() {
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
    @DisplayName("상대방이 블랙잭이 아닌 경우 승리하는지 테스트")
    void showMatchOpponentNotBlackjack() {
        final State anotherState = new Stay(new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.JACK),
                Card.from(Suit.DIAMOND, Denomination.THREE))));

        assertThat(state.match(anotherState)).isEqualTo(BlackjackMatch.WIN);
    }

    @Test
    @DisplayName("상대방이 블랙잭인 경우 무승부하는지 테스트")
    void showMatchOpponentBlackjack() {
        final State anotherState = new Blackjack(cards);

        assertThat(state.match(anotherState)).isEqualTo(BlackjackMatch.DRAW);
    }

    @Test
    @DisplayName("블랙잭으로 이긴 경우 수익률 1.5배 테스트")
    void profitRatio() {
        assertThat(state.profitRate(BlackjackMatch.WIN)).isEqualTo(1.5);
    }
}