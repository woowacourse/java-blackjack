package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    
    private Dealer dealer;
    
    @BeforeEach
    void setUp() {
        dealer = Dealer.create();
        Card firstCard = new Card(Suit.CLOVER, Rank.TEN);
        Card secondCard = new Card(Suit.CLOVER, Rank.SIX);
        
        dealer.receive(firstCard);
        dealer.receive(secondCard);
    }
    
    @Test
    @DisplayName("카드 합이 조건값 이하라면 뽑아야 한다")
    void shouldReceive_LessThanOrEqualToThreshold_ShouldReceive() {
        
        // when
        boolean shouldReceive = dealer.canReceive();
        
        // then
        assertThat(shouldReceive).isTrue();
    }
    
    @Test
    @DisplayName("카드 합이 조건값보다 크다면 뽑지 않아야 한다")
    void shouldReceive_LessThanThreshold_ShouldNotReceive() {
        
        // given
        Card card = new Card(Suit.CLOVER, Rank.ACE);
        dealer.receive(card);
        
        // when
        boolean shouldReceive = dealer.canReceive();
        
        // then
        assertThat(shouldReceive).isFalse();
    }
    
    @Test
    @DisplayName("게임 참가자에게 카드 나눠주기 테스트")
    void deal() {
        
        // given
        BettingMoney bettingMoney = BettingMoney.from("0");
        Player player = Player.of("pobi", bettingMoney);
        
        // when
        dealer.deal(player);
        
        // then
        assertThat(player.getCards()).hasSize(1);
    }
    
    @Test
    void calculateProfit() {
        
        // given
        final BettingMoney bettingMoney = BettingMoney.from("1000");
        Player jason = Player.of("jason", bettingMoney);
        jason.receive(new Card(Suit.CLOVER, Rank.TEN));
        jason.receive(new Card(Suit.CLOVER, Rank.NINE));
    
        Player cu = Player.of("cu", bettingMoney);
        cu.receive(new Card(Suit.SPADE, Rank.TEN));
        cu.receive(new Card(Suit.SPADE, Rank.FIVE));
    
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.HEART, Rank.TEN));
        pobi.receive(new Card(Suit.HEART, Rank.ACE));
    
        List<Player> players = Arrays.asList(jason, cu, pobi);
        
        // when
        double profit = dealer.calculateProfit(players);
        
        // then
        assertThat(profit).isEqualTo(-1000 + 1000 + -1500);
    }
}
