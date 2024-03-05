package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void createCardTest() {
        // given
        Symbol expectedSymbol = Symbol.HEART;
        Number expectedNumber = Number.QUEEN;

        // when
        Card card = new Card(expectedSymbol, expectedNumber);
        Symbol symbol = card.getSymbol();
        Number number = card.getNumber();

        // then
        assertThat(symbol).isEqualTo(Symbol.HEART);
        assertThat(number).isEqualTo(Number.QUEEN);
    }
}
