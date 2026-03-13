package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {
    
    @DisplayName("배팅 금액이 0 이하일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -10000})
    void validateBettingAmount_Exception_WhenInvalidAmount(int invalidAmount) {
        assertThatThrownBy(() -> new Player("pobi", invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액");
    }
    
    @Test
    @DisplayName("플레이어가 Stand를 선언하면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenStand() {
        Player player = new Player("pobi", 10000);
        
        player.stand();
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("플레이어가 Bust 상태이면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenBust() {
        Player player = new Player("pobi", 10000);
        player.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        player.receiveCard(new Card(Rank.TEN, Suit.HEARTS));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUBS));
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("처음 두 장으로 블랙잭이 되면 카드를 뽑을 수 없다.")
    void isDrawable_False_WhenBlackjack() {
        Player player = new Player("pobi", 10000);
        player.receiveCard(new Card(Rank.ACE, Suit.SPADES));
        player.receiveCard(new Card(Rank.KING, Suit.HEARTS));
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @Test
    @DisplayName("전달받은 수익률에 자신의 배팅금을 곱해 최종 수익금을 반환한다.")
    void calculateProfit_ReturnsCorrectAmount() {
        Player player = new Player("pobi", 10000);
        
        assertThat(player.calculateProfit(ProfitRate.WIN_BLACKJACK)).isEqualTo(15000);
        assertThat(player.calculateProfit(ProfitRate.WIN)).isEqualTo(10000);
        assertThat(player.calculateProfit(ProfitRate.DRAW)).isEqualTo(0);
        assertThat(player.calculateProfit(ProfitRate.LOSE)).isEqualTo(-10000);
    }
}
