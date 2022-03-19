package blackjack.model.card;

import static blackjack.model.card.CardNumber.ACE;
import static blackjack.model.card.CardNumber.JACK;
import static blackjack.model.card.CardNumber.TEN;
import static blackjack.model.card.CardNumber.calculateScore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardNumberTest {

    @DisplayName("ACE, JACK의 합은 21 이다.")
    @Test
    void score_21() {
        assertThat(calculateScore(List.of(ACE, JACK))).isEqualTo(21);
    }

    @DisplayName("ACE, JACK, TEN의 합은 21 이다.")
    @Test
    void score_21_2() {
        assertThat(calculateScore(List.of(ACE, JACK, TEN))).isEqualTo(21);
    }

    @DisplayName("ACE, ACE의 합은 12 이다.")
    @Test
    void score_12() {
        assertThat(calculateScore(List.of(ACE, ACE))).isEqualTo(12);
    }
}