package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    
    @Test
    @DisplayName("플레이어가 Stand 상태이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenStand() {
        Player player = Player.from("pobi", 10000);
        player.stand();
        assertThat(player.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("플레이어가 버스트 상태이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenBust() {
        Player player = Player.from("pobi", 10000);
        player.hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        player.hand.addCard(new Card(Rank.TEN, Suit.HEART));
        player.hand.addCard(new Card(Rank.TWO, Suit.CLOVER));
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("플레이어가 블랙잭 상태이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenBlackjack() {
        Player player = Player.from("pobi", 10000);
        player.hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.hand.addCard(new Card(Rank.KING, Suit.HEART));
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("플레이어가 버스트 상태이면 딜러 점수와 무관하게 패배한다.")
    void calculateResult_Lose_WhenPlayerIsBust() {
        Player player = Player.from("pobi", 10000);
        player.hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        player.hand.addCard(new Card(Rank.TEN, Suit.HEART));
        player.hand.addCard(new Card(Rank.TWO, Suit.CLOVER));
        Dealer dealer = new Dealer();
        
        assertThat(dealer.determineGameResult(player).opposite()).isEqualTo(GameResult.LOSE);
    }
    
    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트 상태이면 승리한다.")
    void calculateResult_Win_WhenDealerIsBust() {
        Player player = Player.from("pobi", 10000);
        player.hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        
        Dealer dealer = new Dealer();
        dealer.hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.hand.addCard(new Card(Rank.TEN, Suit.HEART));
        dealer.hand.addCard(new Card(Rank.TWO, Suit.CLOVER));
        
        assertThat(dealer.determineGameResult(player).opposite()).isEqualTo(GameResult.WIN);
    }
    
    @Test
    @DisplayName("둘 다 버스트가 아닐 때 플레이어 점수가 더 높으면 승리한다.")
    void calculateResult_Win_WhenScoreIsHigherThanDealer() {
        Player player = Player.from("pobi", 10000);
        player.hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        player.hand.addCard(new Card(Rank.NINE, Suit.HEART)); // 19
        
        Dealer dealer = new Dealer();
        dealer.hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.hand.addCard(new Card(Rank.EIGHT, Suit.HEART)); // 18
        
        assertThat(dealer.determineGameResult(player).opposite()).isEqualTo(GameResult.WIN);
    }
}
