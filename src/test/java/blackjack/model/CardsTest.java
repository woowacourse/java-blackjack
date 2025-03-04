package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @CsvSource(value = {"2,true", "1,false"})
    @ParameterizedTest
    void 숫자를_알려주면_카드_개수와_동일한지_알려준다(int size, boolean expected) {
        Cards cards = new Cards(
                List.of(
                        new Card(CardType.CLOVER, CardNumber.TWO),
                        new Card(CardType.CLOVER, CardNumber.TEN)
                )
        );

        assertThat(cards.hasSize(size)).isEqualTo(expected);
    }

    @Test
    void 두_개의_카트들을_합친다() {
        Cards cards = new Cards(List.of(new Card(CardType.CLOVER, CardNumber.TEN)));
        Cards otherCards = new Cards(List.of(new Card(CardType.CLOVER, CardNumber.TEN)));

        cards.merge(otherCards);

        assertThat(cards.hasSize(2)).isTrue();
    }

}
