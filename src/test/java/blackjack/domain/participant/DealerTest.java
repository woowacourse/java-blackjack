package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    
    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 카드를 뽑을 수 있다.")
    void isDrawable_True_WhenScoreIsExactly16() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEART));
        
        assertThat(dealer.isDrawable()).isTrue();
    }
    
    @Test
    @DisplayName("딜러의 점수가 17점 이상이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenScoreIsExactly17() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART));
        
        assertThat(dealer.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("플레이어만 블랙잭일 경우 1.5배의 수익을 얻는다.")
    void determinePlayerProfit_WinBlackjack() {
        Player player = Player.from("pobi", 10000);
        player.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        player.receiveCard(new Card(Rank.TEN, Suit.HEART));
        
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.NINE, Suit.HEART));
        
        List<Integer> profits = dealer.determinePlayersProfit(List.of(player));
        
        assertThat(profits.getFirst()).isEqualTo(15000);
    }
    
    @Test
    @DisplayName("딜러와 플레이어가 모두 블랙잭일 경우 무승부 처리된다.")
    void determinePlayerProfit_Draw_WhenBothBlackjack() {
        Player player = Player.from("pobi", 10000);
        player.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        player.receiveCard(new Card(Rank.TEN, Suit.HEART));
        
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.ACE, Suit.CLOVER));
        dealer.receiveCard(new Card(Rank.KING, Suit.DIAMOND));
        
        List<Integer> profits = dealer.determinePlayersProfit(List.of(player));
        
        assertThat(profits.getFirst()).isEqualTo(0);
    }
    
    @Test
    @DisplayName("딜러와 플레이어가 모두 Bust일 경우 플레이어가 승리한다.")
    void determinePlayerProfit_Win_WhenBothBust() {
        Player player = Player.from("pobi", 10000);
        player.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        player.receiveCard(new Card(Rank.TEN, Suit.HEART));
        player.receiveCard(new Card(Rank.TWO, Suit.CLOVER));
        
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.DIAMOND));
        dealer.receiveCard(new Card(Rank.SIX, Suit.CLOVER));
        dealer.receiveCard(new Card(Rank.SIX, Suit.SPADE));
        
        List<Integer> profits = dealer.determinePlayersProfit(List.of(player));
        
        assertThat(profits.getFirst()).isEqualTo(10000);
    }
    
    @Test
    @DisplayName("플레이어들의 수익을 정산하고, 딜러는 그 반대 부호의 최종 수익을 가진다.")
    void determineProfit_DealerZeroSum() {
        Player winPlayer = Player.from("pobi", 10000);
        winPlayer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        winPlayer.receiveCard(new Card(Rank.NINE, Suit.HEART));
        
        Player losePlayer = Player.from("jason", 20000);
        losePlayer.receiveCard(new Card(Rank.TEN, Suit.CLOVER));
        losePlayer.receiveCard(new Card(Rank.SEVEN, Suit.DIAMOND));
        
        Player drawPlayer = Player.from("honux", 30000);
        drawPlayer.receiveCard(new Card(Rank.TEN, Suit.DIAMOND));
        drawPlayer.receiveCard(new Card(Rank.EIGHT, Suit.SPADE));
        
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.HEART));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.CLOVER));
        
        assertThat(dealer.determineProfit(List.of(winPlayer, losePlayer, drawPlayer))).isEqualTo(10000);
    }
}
