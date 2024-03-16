package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.cardinfo.CardNumber;
import domain.card.cardinfo.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드의 숫자가 Ace이면 true를 반환한다.")
    @Test
    void trueWhenCardNumberIsAce() {
        Card card = new Card(CardNumber.ACE, CardShape.CLOVER);
        assertThat(card.isAce()).isTrue();
    }

    @DisplayName("카드의 숫자가 Ace가 아니면 false를 반환한다.")
    @Test
    void falseWhenCardNumberIsAce() {
        Card card = new Card(CardNumber.TEN, CardShape.CLOVER);
        assertThat(card.isAce()).isFalse();
    }

    @DisplayName("카드의 점수를 반환한다.")
    @Test
    void scoreByCard() {
        Card card = new Card(CardNumber.FIVE, CardShape.CLOVER);
        assertThat(card.score()).isEqualTo(5);
    }
}
