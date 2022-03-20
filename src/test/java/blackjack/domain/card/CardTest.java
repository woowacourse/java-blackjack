package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("생성 당시의 문양 값을 반환할 수 있어야 한다.")
    @Test
    void getCardPatternTest() {
        final Card card = new Card(CardPattern.SPADE, CardNumber.ACE);
        assertThat(card.getCardPattern()).isEqualTo(CardPattern.SPADE);
    }

    @DisplayName("생성 당시의 숫자 값을 반환할 수 있어야 한다.")
    @Test
    void getCardNumberTest() {
        final Card card = new Card(CardPattern.SPADE, CardNumber.ACE);
        assertThat(card.getCardNumber()).isEqualTo(CardNumber.ACE);
    }

}