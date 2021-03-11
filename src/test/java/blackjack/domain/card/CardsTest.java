package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.state.CardFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardsTest {
    @DisplayName("카드의 점수를 계산한다.")
    @Test
    void calculateCards() {
        Cards cards = new Cards(SPADE_TEN, SPADE_JACK);
        int score = cards.calculateScore();

        assertThat(score).isEqualTo(20);
    }

    @DisplayName("ACE가 포함됐을 때, 점수가 21을 초과하면 두번째 점수로 계산한다.")
    @Test
    void calculateCardsContainAce() {
        Cards cards = new Cards(SPADE_ACE, SPADE_JACK);
        cards.add(SPADE_TEN);
        int score = cards.calculateScore();

        assertThat(score).isEqualTo(21);
    }
}
