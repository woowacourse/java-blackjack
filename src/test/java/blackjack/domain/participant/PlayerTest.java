package blackjack.domain.participant;

import blackjack.domain.card.Card;
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
        double bettingMoney = 0;
        Player player = Player.of("pobi", bettingMoney);
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
        double bettingMoney = 0;
        Player player = Player.of("pobi", bettingMoney);
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.JACK);
        
        player.receive(firstCard);
        player.receive(secondCard);
        
        // when
        boolean canReceive = player.canReceive();
        
        // then
        assertThat(canReceive).isTrue();
    }
    
    @Test
    @DisplayName("카드 합이 조건보다 작다면 뽑지 않아야 한다")
    void canReceive_GreaterThanThreshold_CannotReceive() {
        
        // given
        double bettingMoney = 0;
        Player player = Player.of("pobi",bettingMoney);
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
}