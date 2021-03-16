package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {
    
    private Dealer dealer;
    private Player pobi;
    private List<Player> players;
    
    @BeforeEach
    void setUp() {
        dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        pobi = Player.of("pobi", BettingMoney.from("1000"));
        players = Collections.singletonList(pobi);
    }
    
    @Test
    @DisplayName("플레이어가 블랙잭으로 승리")
    void calculateProfitOfPlayer_PlayerWinAsBlackjack() {
        
        // given
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        Profit profit = Profit.of(dealer, players);
        double pobiProfit = profit.getProfitOfPlayer(pobi);
        
        // then
        assertThat(pobiProfit).isEqualTo(1500);
    }
    
    @Test
    @DisplayName("플레이어가 딜러보다 카드 패의 합이 커서 승리")
    void calculateProfitOfPlayer_PlayerWinAsNonBust() {
        
        // given
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        Profit profit = Profit.of(dealer, players);
        double pobiProfit = profit.getProfitOfPlayer(pobi);
        
        // then
        assertThat(pobiProfit).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("플레이어와 딜러는 무승부")
    void calculateProfitOfPlayer_PlayerIsDraw() {
        
        // given
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        // when
        Profit profit = Profit.of(dealer, players);
        double pobiProfit = profit.getProfitOfPlayer(pobi);
        
        // then
        assertThat(pobiProfit).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("플레이어의 카드 패 합이 딜러의 카드 패 합보다 작아서 패배")
    void calculateProfitOfPlayer_PlayerSumIsLowerThanDealerSum() {
        
        // given
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.NINE));
        
        // when
        Profit profit = Profit.of(dealer, players);
        double pobiProfit = profit.getProfitOfPlayer(pobi);
        
        // then
        assertThat(pobiProfit).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("플레이어의 카드 패가 버스트")
    void calculateProfitOfPlayer_PlaerIsBust() {
        
        // given
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.TWO));
        
        // when
        Profit profit = Profit.of(dealer, players);
        double pobiProfit = profit.getProfitOfPlayer(pobi);
        
        // then
        assertThat(pobiProfit).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("딜러의 수익 계산 테스트")
    void calculateProfit() {
        
        // given
        final BettingMoney bettingMoney = BettingMoney.from("1000");
        Player jason = Player.of("jason", bettingMoney);
        jason.receive(new Card(Suit.CLOVER, Rank.TEN));
        jason.receive(new Card(Suit.CLOVER, Rank.NINE));
        
        Player cu = Player.of("cu", bettingMoney);
        cu.receive(new Card(Suit.SPADE, Rank.TEN));
        cu.receive(new Card(Suit.SPADE, Rank.TEN));
        
        Player seed = Player.of("seed", bettingMoney);
        seed.receive(new Card(Suit.HEART, Rank.TEN));
        seed.receive(new Card(Suit.HEART, Rank.ACE));
        
        List<Player> players = Arrays.asList(jason, cu, seed);
        Profit profit = Profit.of(dealer, players);
        
        // when
        double dealerProfit = profit.calculateProfitOfDealer();
        
        // then
        assertThat(dealerProfit).isEqualTo(-1500);
    }
}
