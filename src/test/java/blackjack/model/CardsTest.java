package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    void 카드_숫자의_합을_반환한다() {
        Cards cards = new Cards(
                List.of(
                        new Card(CardType.CLOVER, CardNumber.TWO),
                        new Card(CardType.CLOVER, CardNumber.TEN)
                )
        );

        assertThat(cards.sumAll()).isEqualTo(12);
    }

}
