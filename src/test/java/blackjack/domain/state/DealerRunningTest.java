package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.util.BlackjackTestUtil;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerRunningTest {

    @Test
    @DisplayName("처음 점수 합이 17 미만인 카드를 받으면 Running 상태")
    void startedWithRunning() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(16);

        // when
        State actual = DealerRunning.start(cards);

        // then
        assertThat(actual).isInstanceOf(DealerRunning.class);
    }

    @Test
    @DisplayName("딜러가 처음 점수 합이 17 이상인 카드를 받으면 Stand 상태")
    void startedWithStand() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(17);

        // when
        State actual = DealerRunning.start(cards);

        // then
        assertThat(actual).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("점수 합이 21인 카드를 받으면 Blackjack")
    void blackjack() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(21);

        // when
        State actual = DealerRunning.start(cards);

        // then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("카드 합이 17 이상이면 Stand해야 한다.")
    void stand() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(16));
        State running = new DealerRunning(cards);

        // when
        State actual = running.hit(Card.of(Pattern.DIAMOND, Denomination.ACE));

        // then
        assertThat(actual).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("딜러는 자체적인 판단으로 Stand 할 수 없다.")
    void cannotStand() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(16));
        State running = new DealerRunning(cards);

        // then
        assertThatThrownBy(running::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("[ERROR] 딜러는 스스로 Stand할 수 없습니다.");
    }

    @Test
    @DisplayName("hit하고 나서 카드 합이 21을 넘으면 Bust 상태가 된다.")
    void bust() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(16));
        State running = new DealerRunning(cards);

        // when
        State actual = running.hit(Card.of(Pattern.HEART, Denomination.SIX));

        // then
        assertThat(actual).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("hit하고 나서 카드 합이 21 이하면 다시 Running 상태가 된다.")
    void hit() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(15));
        State running = new PlayerRunning(cards);

        // when
        State actual = running.hit(Card.of(Pattern.DIAMOND, Denomination.ACE));

        // then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("끝났는지 물어보면 false를 반환한다.")
    void finishedFalse() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State running = new PlayerRunning(cards);

        // when
        boolean actual = running.isFinished();

        // then
        assertThat(actual).isFalse();
    }
}
