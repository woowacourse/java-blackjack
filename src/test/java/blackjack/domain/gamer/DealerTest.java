package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private Dealer dealer = new Dealer();

    @Test
    @DisplayName("입력 받은 값 보다 크면 true를 반환한다.")
    void isOverThanTrue() {
        dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.ACE));
        assertThat(dealer.isOverThan(10)).isTrue();
    }

    @Test
    @DisplayName("입력 받은 값 보다 작으면 false를 반환한다.")
    void isOverThanFalse() {
        dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.ACE));
        assertThat(dealer.isOverThan(11)).isFalse();
    }
}