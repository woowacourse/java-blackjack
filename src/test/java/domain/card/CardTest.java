package domain.card;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드는 문양과 숫자를 가진다.")
    @Test
    void card() {
        //given
        final var symbol = Symbol.SPADE;
        final var number = Rank.ACE;

        //when //then
        assertThatCode(() -> new Card(symbol, number))
                .doesNotThrowAnyException();
    }

}
