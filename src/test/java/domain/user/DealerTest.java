package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("점수를 계산한다.")
    void 점수_계산() {
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(Symbol.SPADE, CardNumber.TWO));
        cardHand.add(new Card(Symbol.CLOVER, CardNumber.KING));
        Dealer dealer = new Dealer(cardHand);
        assertThat(dealer.calculateScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("이름을 반환한다.")
    void 이름_반환() {
        String nameValue = "딜러";
        Dealer dealer = new Dealer();
        assertThat(dealer.getNameValue()).isEqualTo(nameValue);
    }
}