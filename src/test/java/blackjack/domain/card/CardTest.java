package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    @DisplayName("CarNumber와 CardShape를 입력 후, Card를 가져온다.")
    void getInstance() {
        Card card = new Card(CardShape.CLOVER, CardNumber.FIVE);
        assertThat(card.getName()).isEqualTo("5");
        assertThat(card.getShape()).isEqualTo("클로버");
    }

    @Test
    @DisplayName("Card가 Ace카드인지 확인한다.")
    void isAce() {
        Card card = new Card(CardShape.CLOVER, CardNumber.ACE);
        assertThat(card.isAce()).isTrue();
    }
}