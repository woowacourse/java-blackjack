package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @DisplayName("현재 차례인(카드를 더 받을 수 있는) 플레이어를 찾는다.")
    @Test
    void findDrawablePlayer() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        
        Player firstPlayer = players.findDrawablePlayer();
        
        assertThat(firstPlayer.getNickname()).isEqualTo("pobi");
    }
    
    @DisplayName("플레이어가 카드를 그만 받겠다고 하면 다음 플레이어로 순서가 넘어간다.")
    @Test
    void nextPlayerAfterStop() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        
        players.dontWantDraw();
        
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");
    }
    
    @DisplayName("모든 플레이어가 카드를 그만 받으면 drawable 플레이어는 null이다.")
    @Test
    void noMoreDrawablePlayer() {
        Players players = Players.makePlayers(List.of("pobi"));
        players.dontWantDraw();
        
        assertThat(players.findDrawablePlayer()).isNull();
    }
}