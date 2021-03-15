package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BettingMoneyTest {
    
    @Test
    @DisplayName("베팅 머니 생성 테스트")
    void initTest() {
        
        // given
        String input = "1500";
        
        // when
        BettingMoney bettingMoney = BettingMoney.from(input);
        
        // then
        assertThat(bettingMoney.getMoney()).isEqualTo(1500);
    }
    
    @Test
    @DisplayName("입력값이 숫자가 아닐 경우 예외 발생")
    void initTest_IsNotDigit_ExceptionThrown() {
        
        // given
        String input = "1500$";
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> BettingMoney.from(input);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("숫자만 입력해주세요");
    }
    
    @Test
    @DisplayName("플레이어가 블랙잭으로 승리")
    void calculateProfitOfPlayer_PlayerWinAsBlackjack() {
        
        // given
        BettingMoney bettingMoney = BettingMoney.from("1000");
    
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
    
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        double profit = bettingMoney.calculateProfitOfPlayer(dealer, pobi);
        
        // then
        assertThat(profit).isEqualTo(1500);
    }
    
    @Test
    @DisplayName("플레이어가 딜러보다 카드 패의 합이 커서 승리")
    void calculateProfitOfPlayer_PlayerWinAsNonBust() {
        
        // given
        BettingMoney bettingMoney = BettingMoney.from("1000");
        
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.ACE));
        
        // when
        double profit = bettingMoney.calculateProfitOfPlayer(dealer, pobi);
        
        // then
        assertThat(profit).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("플레이어와 딜러는 무승부")
    void calculateProfitOfPlayer_PlayerIsDraw() {
        
        // given
        BettingMoney bettingMoney = BettingMoney.from("1000");
        
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        
        // when
        double profit = bettingMoney.calculateProfitOfPlayer(dealer, pobi);
        
        // then
        assertThat(profit).isEqualTo(1000);
    }
    
    @Test
    @DisplayName("플레이어의 카드 패 합이 딜러의 카드 패 합보다 작아서 패배")
    void calculateProfitOfPlayer_PlayerSumIsLowerThanDealerSum() {
        
        // given
        BettingMoney bettingMoney = BettingMoney.from("1000");
        
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.NINE));
        
        // when
        double profit = bettingMoney.calculateProfitOfPlayer(dealer, pobi);
        
        // then
        assertThat(profit).isEqualTo(-1000);
    }
    
    @Test
    @DisplayName("플레이어의 카드 패가 버스트")
    void calculateProfitOfPlayer_PlaerIsBust() {
        
        // given
        BettingMoney bettingMoney = BettingMoney.from("1000");
        
        Dealer dealer = Dealer.create();
        dealer.receive(new Card(Suit.HEART, Rank.TEN));
        dealer.receive(new Card(Suit.HEART, Rank.JACK));
        
        Player pobi = Player.of("pobi", bettingMoney);
        pobi.receive(new Card(Suit.DIAMOND, Rank.TEN));
        pobi.receive(new Card(Suit.DIAMOND, Rank.JACK));
        pobi.receive(new Card(Suit.DIAMOND, Rank.TWO));
        
        // when
        double profit = bettingMoney.calculateProfitOfPlayer(dealer, pobi);
        
        // then
        assertThat(profit).isEqualTo(-1000);
    }
}