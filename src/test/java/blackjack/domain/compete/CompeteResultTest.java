package blackjack.domain.compete;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.BettingMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CompeteResultTest {
    
    private final BettingMoney bettingMoney = BettingMoney.from("0");
    
    private Dealer dealer;
    
    @BeforeEach
    void setUpDealer() {
        dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
    }
    
    @Test
    @DisplayName("딜러와 모든 플레이어간의 승패 계산 테스트")
    void compete() {
        
        // given
        Player jason = Player.of("jason", bettingMoney);
        jason.receive(new Card(Suit.CLOVER, Rank.TEN));
        jason.receive(new Card(Suit.CLOVER, Rank.NINE));
        
        Player cu = Player.of("cu", bettingMoney);
        cu.receive(new Card(Suit.SPADE, Rank.TEN));
        cu.receive(new Card(Suit.SPADE, Rank.JACK));
        
        List<Player> players = Arrays.asList(jason, cu);
        
        // when
        final CompeteResultGroup competeResultGroup = CompeteResult.compete(dealer, players);
        final String result = competeResultGroup.getCompeteResultOfDealer();
        
        // then
        assertThat(result).isEqualTo("1승 1무");
    }
    
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합보다 클 경우 승리")
    void compete_playerSumGreaterThanDealerSum_Win() {
        
        // given
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        List<Player> players = Collections.singletonList(pobi);
        
        // when
        final CompeteResultGroup competeResultGroup = CompeteResult.compete(dealer, players);
        final String result = competeResultGroup.getCompeteResultOfPlayer(pobi);
        
        // then
        assertThat(result).isEqualTo("승");
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합보다 작을 경우 패배")
    void compete_playerSumLessThanDealerSum_Defeat() {
        
        // given
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.CLOVER, Rank.TEN));
        pobi.receive(new Card(Suit.CLOVER, Rank.FIVE));
        
        List<Player> players = Collections.singletonList(pobi);
        
        // when
        final CompeteResultGroup competeResultGroup = CompeteResult.compete(dealer, players);
        final String result = competeResultGroup.getCompeteResultOfPlayer(pobi);
        
        // then
        assertThat(result).isEqualTo("패");
    }
    
    @Test
    @DisplayName("플레이어의 카드 합이 딜러의 합과 같을 경우 무승부")
    void compete_playerSumEqualToDealerSum_Draw() {
        
        // given
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        List<Player> players = Collections.singletonList(pobi);
        
        // when
        final CompeteResultGroup competeResultGroup = CompeteResult.compete(dealer, players);
        final String result = competeResultGroup.getCompeteResultOfPlayer(pobi);
        
        // then
        assertThat(result).isEqualTo("무");
    }
    
    @Test
    @DisplayName("플레이어가 버스트일 경우 무조건 플레이어 패배")
    void compete_isBust_Defeat() {
        
        // given
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        List<Player> players = Collections.singletonList(pobi);
        
        // when
        final CompeteResultGroup competeResultGroup = CompeteResult.compete(dealer, players);
        final String result = competeResultGroup.getCompeteResultOfPlayer(pobi);
        
        // then
        assertThat(result).isEqualTo("패");
    }
}