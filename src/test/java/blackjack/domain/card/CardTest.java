package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void testCreateCard() {
        Card card = new Card(Type.DIAMOND, Denomination.FOUR);
        assertThat(card).isEqualTo(new Card(Type.DIAMOND, Denomination.FOUR));
    }

    @DisplayName("카드가 올바른 Type과 Denomination을 가지고 있는지 확인한다.")
    @Test
    void testCardInformation() {
        Card card = new Card(Type.DIAMOND, Denomination.FOUR);
        assertThat(card.findDenominationValue()).isEqualTo(4);
        assertThat(card.findTypeName()).isEqualTo("다이아몬드");
    }
}