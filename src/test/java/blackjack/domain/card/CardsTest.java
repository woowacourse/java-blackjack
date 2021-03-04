package blackjack.domain.card;

import blackjack.exception.CardDuplicateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @Test
    @DisplayName("동일한 카드를 넣을 수 없다.")
    void createCards_duplicationException() {
        Assertions.assertThatThrownBy(() -> new Cards(Arrays.asList(
            new Card(Symbol.CLOVER, CardNumber.EIGHT),
            new Card(Symbol.CLOVER, CardNumber.EIGHT)
        ))).isInstanceOf(CardDuplicateException.class);
    }

    @Test
    @DisplayName("카드를 한장씩 가져올 수 있다.")
    void next() {
        Cards cards = new Cards(Arrays.asList(
                new Card(Symbol.CLOVER, CardNumber.EIGHT),
                new Card(Symbol.CLOVER, CardNumber.SIX)
        ));

        assertThat(cards.next().getName()).isEqualTo("8클로버");
        assertThat(cards.next().getName()).isEqualTo("6클로버");
    }

}
