package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardTest {

    @Test
    @DisplayName("카드 생성")
    void cardTest() {
        Card card = new Card(CardSymbol.ACE, CardType.SPADE);
        assertThat(card.toString()).isEqualTo("A스페이드");
    }

    @Test
    @DisplayName("카드 생성시 Symbol과 Type이 null이면 예외 발생")
    void cardException() {
        assertThatThrownBy(() -> new Card(null, CardType.DIAMOND))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Card(CardSymbol.ACE, null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Card(null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("카드 심볼을 반환")
    void getCardSymbol() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        assertThat(aceSpade.getSymbol()).isEqualTo("A");
    }

    @Test
    @DisplayName("카드 타입을 반환")
    void getCardType() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        assertThat(aceSpade.getType()).isEqualTo("스페이드");
    }
}