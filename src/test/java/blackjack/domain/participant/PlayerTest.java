package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    
    @DisplayName("플레이어가 정상적으로 생성된다.")
    @Test
    void createPlayer() {
        Player player = new Player("pobi");
        
        assertThat(player.getNickname()).isEqualTo("pobi");
    }
    
    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다 (버스트 시 불가).")
    @Test
    void isDrawable() {
        Player player = new Player("pobi");
        
        assertThat(player.isDrawable()).isTrue();
        
        List<Card> bustedCards = List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.TWO, Suit.DIAMOND)
        );
        player.receiveCard(bustedCards);
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @DisplayName("플레이어의 점수가 21점 미만이면 카드를 받을 수 있다.")
    @Test
    void isDrawableWhenNotBustedOrNotBlackjack() {
        Player player = new Player("pobi");
        List<Card> cards = List.of(
                new Card(Rank.EIGHT, Suit.SPADE),
                new Card(Rank.NINE, Suit.HEART)
        );
        
        player.receiveCard(cards);
        
        assertThat(player.isDrawable()).isTrue();
    }
    
    @DisplayName("플레이어의 점수가 21점(블랙잭 포함) 이상이면 더 이상 카드를 받을 수 없다.")
    @Test
    void isNotDrawableWhenBustedOrBlackjack() {
        Player player = new Player("pobi");
        List<Card> blackjackCards = List.of(
                new Card(Rank.ACE, Suit.SPADE),
                new Card(Rank.JACK, Suit.HEART)
        );
        
        player.receiveCard(blackjackCards);
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @DisplayName("플레이어가 명시적으로 stop()을 호출하면 점수와 상관없이 카드를 받을 수 없다.")
    @Test
    void stopDrawing() {
        Player player = new Player("pobi");
        
        player.stop();
        
        assertThat(player.isDrawable()).isFalse();
    }
}