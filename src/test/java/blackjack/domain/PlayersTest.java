package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @DisplayName("이름 목록으로 플레이어 일급 컬렉션을 생성한다.")
    @Test
    void createPlayers() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        
        assertThat(players.getAllPlayers()).hasSize(2);
        assertThat(players.getAllPlayerNickname()).containsExactly("pobi", "jason");
    }
    
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    @Test
    void validateEmptyName() {
        assertThatThrownBy(() -> Players.makePlayers(List.of("pobi", "")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 될 수 없습니다.");
    }
    
    @DisplayName("카드를 받을 수 있는 플레이어를 순서대로 찾는다.")
    @Test
    void findDrawablePlayer() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("pobi");
        
        players.dontWandDraw();
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");
        
        players.dontWandDraw();
        assertThat(players.findDrawablePlayerNickname()).isNull();
    }
}
