package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.util.BlackjackTestUtil;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @Test
    @DisplayName("처음 카드를 받아서 생성하면 Running 상태")
    void started() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);

        // when
        State actual = Running.start(cards);

        // then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("점수 합이 21인 카드를 받으면 Blackjack")
    void blackjack() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(21);

        // when
        State actual = Running.start(cards);

        // then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("hit하고 나서 카드 합이 21을 넘으면 Bust 상태가 된다.")
    void bust() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State hit = new Running(cards);

        // when
        State actual = hit.hit(Card.of(Pattern.DIAMOND, Denomination.TWO));

        // then
        assertThat(actual).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("hit하고 나서 카드 합이 21 이하면 다시 Running 상태가 된다.")
    void hit() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State hit = new Running(cards);

        // when
        State actual = hit.hit(Card.of(Pattern.DIAMOND, Denomination.ACE));

        // then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("Hit 상태에서 stand하면 Stand 상태가 된다.")
    void stand() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State hit = new Running(cards);

        // when
        State actual = hit.stand();

        // then
        assertThat(actual).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("끝났는지 물어보면 false를 반환한다.")
    void finishedFalse() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State hit = new Running(cards);

        // when
        boolean actual = hit.isFinished();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Blackjack인지 물어보면 false를 반환한다.")
    void blackjackFalse() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State hit = new Running(cards);

        // when
        boolean actual = hit.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Bust인지 물어보면 false를 반환한다.")
    void bustFalse() {
        // given
        Cards cards = new Cards(BlackjackTestUtil.createCards(20));
        State hit = new Running(cards);

        // when
        boolean actual = hit.isBust();

        // then
        assertThat(actual).isFalse();
    }
}
