package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    
    @Test
    @DisplayName("딜러의 점수가 정확히 16점이면 카드를 뽑을 수 있다.")
    void isDrawable_True_WhenScoreIsExactly16() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEART));
        
        assertThat(dealer.isDrawable()).isTrue();
    }
    
    @Test
    @DisplayName("딜러의 점수가 정확히 17점이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenScoreIsExactly17() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART));
        
        assertThat(dealer.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("플레이어들을 상대로 딜러 관점의 승무패 빈도를 계산하여 반환한다.")
    void calculateResult_GroupResultsByPlayerScores() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.EIGHT, Suit.HEART));
        
        Player winPlayer = Player.from("pobi", 10000);
        winPlayer.receiveCard(new Card(Rank.TEN, Suit.CLOVER));
        winPlayer.receiveCard(new Card(Rank.NINE, Suit.DIAMOND));
        
        Player drawPlayer = Player.from("jason", 20000);
        drawPlayer.receiveCard(new Card(Rank.TEN, Suit.CLOVER));
        drawPlayer.receiveCard(new Card(Rank.EIGHT, Suit.DIAMOND));
        
        Player losePlayer = Player.from("honux", 30000);
        losePlayer.receiveCard(new Card(Rank.TEN, Suit.CLOVER));
        losePlayer.receiveCard(new Card(Rank.FIVE, Suit.DIAMOND));
        
        Map<GameResult, Long> dealerResult = dealer.determineGameResult(List.of(winPlayer, drawPlayer, losePlayer));
        
        assertThat(dealerResult.get(GameResult.LOSE)).isEqualTo(1L);
        assertThat(dealerResult.get(GameResult.WIN)).isEqualTo(1L);
    }
}
