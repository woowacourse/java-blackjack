package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.util.BlackjackTestUtil;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {

    @Test
    @DisplayName("처음 카드를 받아서 생성하면 Started 상태")
    void started() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);

        // when
        Object actual = Started.start(cards);

        // then
        assertThat(actual).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("점수 합이 21인 카드를 받으면 Blackjack")
    void blackjack() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(21);

        // when
        Object actual = Started.start(cards);

        // then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("Started 상태에서는 hit을 할 수 있다.")
    void hit() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);
        State started = Started.start(cards);

        // when
        State actual = started.hit(Card.of(Pattern.CLOVER, Denomination.ACE));

        // then
        assertThat(actual).isInstanceOf(Hit.class);
    }

    @Test
    @DisplayName("Started 상태에서는 hit 할 때 카드 점수 합이 21을 넘으면 Bust가 된다.")
    void bust() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);
        State started = Started.start(cards);

        // when
        State actual = started.hit(Card.of(Pattern.CLOVER, Denomination.TWO));

        // then
        assertThat(actual).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("Started 상태에서는 stand를 할 수 있다.")
    void stand() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);
        State started = Started.start(cards);

        // when
        State actual = started.stand();

        // then
        assertThat(actual).isInstanceOf(Stand.class);
    }

    @Test
    @DisplayName("끝났는지 물어보면 false를 반환한다.")
    void finishedFalse() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);
        State started = Started.start(cards);

        // when
        boolean actual = started.isFinished();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Blackjack인지 물어보면 false를 반환한다.")
    void blackjackFalse() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);
        State started = Started.start(cards);

        // when
        boolean actual = started.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Bust인지 물어보면 false를 반환한다.")
    void bustFalse() {
        // given
        List<Card> cards = BlackjackTestUtil.createCards(20);
        State started = Started.start(cards);

        // when
        boolean actual = started.isBust();

        // then
        assertThat(actual).isFalse();
    }
}
