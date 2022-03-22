package blackjack.domain.user.state;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.HandFixtures;
import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

class StateTest {

    private static final State CONCRETE_STATE = new Stay(HandFixtures.STAY_HAND_15);


    @Test
    @DisplayName("점수를 얻을 수 있다.")
    public void testGettingScore() {
        // given
        State state = CONCRETE_STATE;
        // when
        Score score = state.getScore();
        // then
        assertThat(score.getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("블랙잭 여부를 확인할 수 있다.")
    public void testIfStateBlackJack() {
        // given
        State state = CONCRETE_STATE;

        // when
        boolean isBlackjack = state.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }

    @Test
    @DisplayName("카드 목록을 얻을 수 있다.")
    public void testGettingHandCards() {
        // given
        State state = CONCRETE_STATE;
        // when
        List<Card> cards = state.getHandCards();
        // then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("초기 카드 목록을 얻을 수 있따.")
    public void testGettingInitialHandCards() {
        // given
        State state = CONCRETE_STATE;
        // when
        List<Card> cards = state.getInitHandCards(1);
        // then
        assertThat(cards.size()).isEqualTo(1);
    }
}