package blackjack.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
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
}
