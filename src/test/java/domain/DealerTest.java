package domain;

import domain.Card.Card;
import domain.Card.CardNumber;
import domain.Card.CardShape;
import domain.user.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    
    @Test
    @DisplayName("처음에 카드를 지급받지 않은 경우 카드 조회시 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Dealer dealer = new Dealer();
        Assertions.assertThatThrownBy(dealer::getReadyCards)
                .isExactlyInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("처음에 지급받은 카드를 반환한다.")
    void getReadyCardsTestSuccess() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(dealer.getReadyCards())
                .containsExactly(new Card(CardNumber.ACE, CardShape.SPADE));
    }
    
    @Test
    @DisplayName("딜러의 카드가 현재 16점 이하인지 확인한다.")
    void testDealerScore() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(dealer.isAbleToDraw()).isTrue();
        dealer.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(dealer.isAbleToDraw()).isFalse();
    }
}
