package blackjack.domain.card;

import blackjack.domain.participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardHandTest {
    
    @Test
    @DisplayName("카드 패의 합 계산 테스트")
    void sumCardHand() {
        
        // given
        Dealer dealer = Dealer.create();
        Card firstCard = new Card(Suit.CLOVER, Rank.ACE);
        Card secondCard = new Card(Suit.CLOVER, Rank.SIX);
        Card thirdCard = new Card(Suit.DIAMOND, Rank.ACE);
        
        dealer.receive(firstCard);
        dealer.receive(secondCard);
        dealer.receive(thirdCard);
        
        // when
        int sum = dealer.sumCardHand();
        
        // then
        assertThat(sum).isEqualTo(18);
    }
    
    @Test
    @DisplayName("A가 11일 때 버스트일 경우, A를 1로 취급")
    void sumCardHand_GreaterThanThreshold_AceIsOne() {
        
        // given
        Dealer dealer = Dealer.create();
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.SIX);
        Card thirdCard = new Card(Suit.CLOVER, Rank.ACE);
        
        dealer.receive(firstCard);
        dealer.receive(secondCard);
        dealer.receive(thirdCard);
        
        // when
        int sum = dealer.sumCardHand();
        
        // then
        assertThat(sum).isEqualTo(17);
    }
}
