package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CompeteResult;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    
    @Test
    @DisplayName("플레이어 카드 받기")
    void receive() {
        
        // given
        Player player = Player.from("pobi");
        Card card = new Card(Suit.CLOVER, Rank.ACE);
        
        // when
        player.receive(card);
        
        // then
        assertThat(player.getCards()).containsExactly(card);
    }
    
    @Test
    @DisplayName("카드 합이 조건보다 작다면 뽑을 수 있다")
    void canReceive_LessThanOrEqualToThreshold_CanReceive() {
        
        // given
        Player player = Player.from("pobi");
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.JACK);
        Card thirdCard = new Card(Suit.CLOVER, Rank.ACE);
        
        player.receive(firstCard);
        player.receive(secondCard);
        player.receive(thirdCard);
        
        // when
        boolean canReceive = player.canReceive();
        
        // then
        assertThat(canReceive).isTrue();
    }
    
    @Test
    @DisplayName("카드 합이 조건보다 작다면 뽑지 않아야 한다")
    void canReceive_GreaterThanThreshold_CannotReceive() {
        
        // given
        Player player = Player.from("pobi");
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.JACK);
        Card thirdCard = new Card(Suit.CLOVER, Rank.ACE);
        Card fourthCard = new Card(Suit.CLOVER, Rank.ACE);
        
        player.receive(firstCard);
        player.receive(secondCard);
        player.receive(thirdCard);
        player.receive(fourthCard);
        
        // when
        boolean canReceive = player.canReceive();
        
        // then
        assertThat(canReceive).isFalse();
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합보다 클 경우 승리")
    void compete_playerSumGreaterThanDealerSum_Win() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        CompeteResult result = pobi.compete(dealer);
        
        // then
        assertThat(result).isEqualTo(CompeteResult.WIN);
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합보다 작을 경우 패배")
    void compete_playerSumLessThanDealerSum_Defeat() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.CLOVER, Rank.TEN));
        pobi.receive(new Card(Suit.CLOVER, Rank.FIVE));
        
        // when
        CompeteResult result = pobi.compete(dealer);
        
        // then
        assertThat(result).isEqualTo(CompeteResult.DEFEAT);
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합과 같을 경우 무승부")
    void compete_playerSumEqualToDealerSum_Draw() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        // when
        CompeteResult result = pobi.compete(dealer);
        
        // then
        assertThat(result).isEqualTo(CompeteResult.DRAW);
    }
    
    @Test
    @DisplayName("플레이어가 버스트일 경우 무조건 플레이어 패배")
    void compete_isBurst_Defeat() {
        
        // given
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        
        Player pobi = Player.from("pobi");
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        // when
        CompeteResult result = pobi.compete(dealer);
        
        // then
        assertThat(result).isEqualTo(CompeteResult.DEFEAT);
    }
}