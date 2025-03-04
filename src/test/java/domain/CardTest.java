package domain;

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
}
