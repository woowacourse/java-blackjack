package blackjack.model.card;

import static blackjack.model.card.CardNumber.ACE;
import static blackjack.model.card.CardNumber.JACK;
import static blackjack.model.card.CardNumber.TEN;
import static blackjack.model.card.CardNumber.calculateScore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardNumberTest {

    @ParameterizedTest(name = "{0}과 {1}을 합치면 21이다.")
    @CsvSource(value = {"ACE,JACK", "ACE,TEN", "ACE,KING"})
    void score_21(CardNumber first, CardNumber two) {
        assertThat(calculateScore(List.of(first, two))).isEqualTo(21);
    }

    @ParameterizedTest(name = "{0}과 {1}을 합치면 21이 아니다.")
    @CsvSource(value = {"ACE,NINE", "TWO,EIGHT", "ACE,ACE"})
    void score_not_21(CardNumber first, CardNumber two) {
        assertThat(calculateScore(List.of(first, two))).isNotEqualTo(21);
    }

    @DisplayName("ACE, JACK, TEN의 합은 21 이다.")
    @Test
    void score_21_2() {
        assertThat(calculateScore(List.of(ACE, JACK, TEN))).isEqualTo(21);
    }
}