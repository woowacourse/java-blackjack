package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("카드들의 숫자 합을 구한다.")
    @Test
    void calculateCardNumbersSum() {
        final Cards cards = new Cards(List.of(
                new Card(Number.ACE, Suit.DIAMOND),
                new Card(Number.EIGHT, Suit.CLOVER)
        ));

        final int actual = cards.sum();

        assertThat(actual).isEqualTo(19);
    }
}
