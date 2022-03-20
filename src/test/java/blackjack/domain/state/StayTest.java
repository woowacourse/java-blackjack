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

class StayTest {

    private final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.KING),
            Card.from(Suit.DIAMOND, Denomination.SEVEN)));
    final Status status = new Stay(cards);

    @Test
    @DisplayName("카드를 그만 받는 상황일 때 다시 카드를 받으려 하는 경우 예외 발생 테스트")
    void drawIfStay() {
        assertThatThrownBy(() -> status.draw(Card.from(Suit.HEART, Denomination.FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 받을 수 없습니다.");
    }

    @Test
    @DisplayName("카드를 그만 받는 상황일 때 다시 카드를 그만 받으려 하는 경우 예외 발생 테스트")
    void stayIfStay() {
        assertThatThrownBy(status::stay)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 결과입니다.");
    }

    @Test
    @DisplayName("턴이 끝난 상태로 나타내는지 테스트")
    void isFinished() {
        assertThat(status.isFinished()).isTrue();
    }

    @Test
    @DisplayName("턴이 진행중인 상태로 나타내지 않는지 테스트")
    void isRunning() {
        assertThat(status.isRunning()).isFalse();
    }

    @Test
    @DisplayName("상대방이 버스트인 경우 이기는지 테스트")
    void showMatchOpponentIsBust() {
        final Cards cards = new Cards(Set.of(Card.from(Suit.SPADE, Denomination.JACK),
                Card.from(Suit.HEART, Denomination.JACK), Card.from(Suit.CLOVER, Denomination.FIVE)));
        final Status anotherStatus = new Bust(cards);

        assertThat(status.showMatch(anotherStatus)).isEqualTo(BlackjackMatch.WIN);
    }

    @Test
    @DisplayName("둘 다 Stay이고 상대가 점수가 더 낮은 경우 이기는지 테스트")
    void showMatchWinByScore() {
        final Cards cards = new Cards(Set.of(Card.from(Suit.SPADE, Denomination.ACE),
                Card.from(Suit.CLOVER, Denomination.FIVE)));
        final Status anotherStatus = new Stay(cards);

        assertThat(status.showMatch(anotherStatus)).isEqualTo(BlackjackMatch.WIN);
    }

    @Test
    @DisplayName("둘 다 Stay이고 상대와 점수가 같은 경우 비기는지 테스트")
    void showMatchDrawByScore() {
        final Cards cards = new Cards(Set.of(Card.from(Suit.CLOVER, Denomination.ACE),
                Card.from(Suit.CLOVER, Denomination.SIX)));
        final Status anotherStatus = new Stay(cards);

        assertThat(status.showMatch(anotherStatus)).isEqualTo(BlackjackMatch.DRAW);
    }

    @Test
    @DisplayName("둘 다 Stay이고 상대가 점수가 더 높은 경우 지는지 테스트")
    void showMatchLoseByScore() {
        final Cards cards = new Cards(Set.of(Card.from(Suit.SPADE, Denomination.ACE),
                Card.from(Suit.HEART, Denomination.SEVEN)));
        final Status anotherStatus = new Stay(cards);

        assertThat(status.showMatch(anotherStatus)).isEqualTo(BlackjackMatch.LOSE);
    }

    @Test
    @DisplayName("Stay 상태에서 이긴 경우 수익률 1배 테스트")
    void winProfitRatio() {
        assertThat(status.profitRate(BlackjackMatch.WIN)).isEqualTo(1);
    }

    @Test
    @DisplayName("Stay 상태에서 비긴 경우 수익률 0배 테스트")
    void drawProfitRatio() {
        assertThat(status.profitRate(BlackjackMatch.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("Stay 상태에서 진 경우 수익률 -1배 테스트")
    void loseProfitRatio() {
        assertThat(status.profitRate(BlackjackMatch.LOSE)).isEqualTo(-1);
    }
}