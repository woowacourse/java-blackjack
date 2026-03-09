package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    
    @DisplayName("딜러가 정상적으로 생성된다.")
    @Test
    void createDealer() {
        Dealer dealer = new Dealer();
        
        assertThat(dealer.getNickname()).isEqualTo("딜러");
    }
    
    @DisplayName("딜러는 총합이 16점 이하일 때 카드를 받는다.")
    @Test
    void isDealerDraw() {
        Dealer dealer = new Dealer();
        List<Card> cards16 = List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.SIX, Suit.HEART)
        );
        
        dealer.receiveCard(cards16);
        assertThat(dealer.isDrawable()).isTrue();
    }
    
    @DisplayName("딜러는 총합이 16점 초과일 때 카드를 받지 않는다.")
    @Test
    void isDealerNotDraw() {
        Dealer dealer = new Dealer();
        List<Card> cards17 = List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.SEVEN, Suit.HEART)
        );
        
        dealer.receiveCard(cards17);
        assertThat(dealer.isDrawable()).isFalse();
    }
    
    @DisplayName("딜러의 첫 번째 카드 이름을 반환한다.")
    @Test
    void getFirstCard() {
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART)
        );
        
        dealer.receiveCard(cards);
        
        assertThat(dealer.getFirstCardInfoSnapshot()).isEqualTo("딜러카드: A스페이드");
    }
}
