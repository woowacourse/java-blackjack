package domain.deck;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @DisplayName("주어진 카드의 점수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "SPADE,ACE,1", "SPADE,THREE,3", "SPADE,JACK,10"
    })
    void 주어진_카드의_점수를_반환한다(final Shape shape, final Rank rank, final int expected) {

        // given
        final Card card = new Card(rank, shape);

        // when & then
        Assertions.assertThat(card.getScore()).isEqualTo(expected);
    }

    @DisplayName("카드가 에이스면 true 아니면 false를 반환한다")
    @ParameterizedTest
    @CsvSource(value = {
            "ACE, SPADE, true", "THREE, SPADE, false", "JACK, SPADE, false"
    })
    void 카드가_예이스면_true_아니면_false를_반환한다(final Rank rank, final Shape shape, final boolean expected) {

        // given
        // when & then
        assertThat(new Card(rank, shape).isAce()).isEqualTo(expected);
    }
}
