package blackjack.player.card;

import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @DisplayName("카드가 ACE 인지 아닌지 확인")
    @ParameterizedTest
    @CsvSource(value = {"ACE,true", "TWO,false"})
    void isAce(CardNumber cardNumber, boolean result) {
        Card card = new Card(Symbol.HEART, cardNumber);
        assertThat(card.isAce()).isEqualTo(result);
    }

    @DisplayName("카드의 점수를 반환하는 기능")
    @ParameterizedTest
    @CsvSource(value = {"ACE,1", "TEN,10", "KING,10"})
    void name(CardNumber cardNumber, int result) {
        Card card = new Card(Symbol.CLUB, cardNumber);
        assertThat(card.getScore()).isEqualTo(result);
    }
}