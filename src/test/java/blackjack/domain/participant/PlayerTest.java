package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.PlayingCards;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    
    @DisplayName("플레이어가 정상적으로 생성된다.")
    @Test
    void createPlayer() {
        Player player = new Player("포비", Role.PLAYER);
        assertThat(player.getNickname()).isEqualTo("포비");
        assertThat(player.isDealer()).isFalse();
    }
    
    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다.")
    @Test
    void isDrawable() {
        Player player = new Player("포비", Role.PLAYER);
        
        // 초기 상태는 받을 수 있음 (0점)
        assertThat(player.isDrawable()).isTrue();
        
        // 21점 이상이면 더 받을 수 없음 (버스트 상태)
        PlayingCards bustedCards = PlayingCards.from(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));
        player.receiveCard(bustedCards);
        
        assertThat(player.isDrawable()).isFalse();
    }
    
    @DisplayName("플레이어가 명시적으로 stop()을 호출하면 점수와 상관없이 카드를 받을 수 없다.")
    @Test
    void stopDrawing() {
        Player player = new Player("포비", Role.PLAYER);
        
        player.stop();
        
        assertThat(player.isDrawable()).isFalse();
    }
}