package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("카드")
public class CardTest {

    @DisplayName("숫자와 기호를 가진 카드 조회에 성공한다.")
    @Test
    void createCard() {
        //given
        int number = 4;
        Symbol symbol = Symbol.CLOVER;

        //when
        Card card = new Card(number, symbol);

        //then
        assertThat(card.getNumber())
                .isEqualTo(number);
        assertThat(card.getSymbol())
                .isEqualTo(symbol);
    }
}
