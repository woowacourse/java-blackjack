package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("카드 한 장 생성 확인")
    void createCardTest() {
        // given
        Figure spade = Figure.SPADE;
        Number five = Number.FIVE;
        // when
        Card card = new Card(spade, five);
        // then
        assertThat(card.getCardName()).isEqualTo("5스페이드");
    }

}
