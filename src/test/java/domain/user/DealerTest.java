package domain.user;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    
    @Test
    @DisplayName("딜러 생성 테스트")
    void create() {
        Dealer dealer = new Dealer();
        Assertions.assertThat(dealer.getName()).isEqualTo("딜러");
    }
    
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
    
    @Test
    @DisplayName("현재의 점수를 반환한다.")
    void calculateScore() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(dealer.getScore()).isEqualTo(14);
    }
    
    @Test
    @DisplayName("현재 점수로부터 딜러 상태를 반환한다.")
    void getStatus() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.TWO, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(dealer.getStatus()).isEqualTo(MemberStatus.NOT_BUST);
        dealer.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        Assertions.assertThat(dealer.getStatus()).isEqualTo(MemberStatus.BUST);
        Assertions.assertThat(dealer.isBust()).isTrue();
    }
    
    @Test
    @DisplayName("딜러가 블랙잭인지 확인한다.")
    void testDealerBlackJack() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(dealer.isBlackJack()).isTrue();
    }
    
    @Test
    @DisplayName("딜러가 21점이여도 블랙잭이 아닌지 확인한다.")
    void testDealerNotBlackJack() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.KING, CardShape.HEART));
        dealer.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        Assertions.assertThat(dealer.isBlackJack()).isFalse();
    }
}
