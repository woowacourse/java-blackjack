package blackjack.domain.card;

import blackjack.exception.CardDuplicateException;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @Test
    @DisplayName("동일한 카드를 넣을 수 없다.")
    void createCards_duplicationException() {
        Assertions.assertThatThrownBy(() -> new Cards(Arrays.asList(
            new Card(Symbol.CLOVER, CardNumber.EIGHT),
            new Card(Symbol.CLOVER, CardNumber.EIGHT)
        ))).isInstanceOf(CardDuplicateException.class);
    }

}
