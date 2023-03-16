package domain.user;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.money.Bet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    
    @Test
    @DisplayName("플레이어 생성 테스트")
    void create() {
        Player participant = new Player("echo");
        Assertions.assertThat(participant.getName()).isEqualTo("echo");
    }
    
    @Test
    @DisplayName("처음에 카드를 지급받지 않은 경우 카드 조회시 오류를 던진다.")
    void getReadyCardsTestFailed() {
        Player participant = new Player("echo");
        Assertions.assertThatThrownBy(participant::getReadyCards)
                .isExactlyInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("처음에 지급받은 카드를 반환한다.")
    void getReadyCardsTestSuccess() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(participant.getReadyCards())
                .containsExactly(new Card(CardNumber.ACE, CardShape.SPADE),
                        new Card(CardNumber.THREE, CardShape.HEART));
    }
    
    @Test
    @DisplayName("현재의 점수를 반환한다.")
    void calculateScore() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.THREE, CardShape.HEART));
        Assertions.assertThat(participant.getScore()).isEqualTo(14);
    }
    
    @Test
    @DisplayName("현재 점수로부터 참여자 상태를 반환한다.")
    void getStatus() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.TWO, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(participant.getStatus()).isEqualTo(MemberStatus.NOT_BUST);
        participant.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        Assertions.assertThat(participant.getStatus()).isEqualTo(MemberStatus.BUST);
        Assertions.assertThat(participant.isBust()).isTrue();
    }
    
    @Test
    @DisplayName("드로우 할 수 있는지 확인한다.")
    void isAbleToDraw() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.TWO, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(participant.isAbleToDraw()).isTrue();
        participant.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        Assertions.assertThat(participant.isAbleToDraw()).isFalse();
    }
    
    @Test
    @DisplayName("배팅 금액을 추가한다.")
    void addBet() {
        Player participant = new Player("echo");
        participant.addBet(new Bet(1000));
        Assertions.assertThat(participant.getBet()).isEqualTo(new Bet(1000));
    }
    
    @Test
    @DisplayName("버스트인지 확인한다.")
    void isBust() {
        Player participant = new Player("echo");
        participant.addCard(new Card(CardNumber.TWO, CardShape.SPADE));
        participant.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(participant.isBust()).isFalse();
        participant.addCard(new Card(CardNumber.KING, CardShape.DIAMOND));
        Assertions.assertThat(participant.isBust()).isTrue();
    }
    
    @Test
    @DisplayName("Player가 블랙잭인지 확인한다.")
    void testDealerBlackJack() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.ACE, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.KING, CardShape.HEART));
        Assertions.assertThat(dealer.isBlackJack()).isTrue();
    }
    
    @Test
    @DisplayName("Player가 21점이여도 블랙잭이 아닌지 확인한다.")
    void testDealerNotBlackJack() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(CardNumber.KING, CardShape.SPADE));
        dealer.addCard(new Card(CardNumber.KING, CardShape.HEART));
        dealer.addCard(new Card(CardNumber.ACE, CardShape.HEART));
        Assertions.assertThat(dealer.isBlackJack()).isFalse();
    }
}
