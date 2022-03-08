package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @ParameterizedTest
    @CsvSource(value = {"DIAMOND", "CLUB", "HEART", "SPADE"})
    @DisplayName("주어진 모양과 숫자의 카드를 생성한다.")
    void createCard(CardSymbol symbol) {
        // give
        final Card card = new Card(symbol, CardNumber.ACE);

        // when
        CardSymbol actual = card.getSymbol();

        // then
        assertThat(actual).isEqualTo(symbol);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE", "TEN", "KING"})
    @DisplayName("주어진 숫자의 카드를 생성한다")
    void createCard_number(CardNumber cardNumber) {
        // give
        final Card card = new Card(CardSymbol.DIAMOND, cardNumber);

        // when
        CardNumber actual = card.getNumber();

        // then
        assertThat(actual).isEqualTo(cardNumber);
    }
}