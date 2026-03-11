package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @Test
    @DisplayName("중복된 이름을 가진 플레이어들로 객체를 생성하면 예외가 발생한다.")
    void createPlayers_ThrowException_WhenNameIsDuplicated() {
        List<String> names = List.of("pobi", "pobi");
        
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.fromNames(names))
                .withMessageContaining("중복될 수 없습니다");
    }
    
    @Test
    @DisplayName("카드를 뽑을 수 있는 첫 번째 플레이어를 반환한다.")
    void getDrawablePlayer_ReturnPlayer_WhenDrawablePlayerExists() {
        Players players = Players.fromNames(List.of("pobi", "jason"));
        players.getPlayers().get(0).stand();
        
        Player drawablePlayer = players.getDrawablePlayer();
        assertThat(drawablePlayer.getNickname()).isEqualTo("jason");
    }
    
    @Test
    @DisplayName("모든 플레이어가 카드를 뽑을 수 없는 상태이면 null을 반환한다.")
    void getDrawablePlayer_ReturnNull_WhenAllPlayersAreNotDrawable() {
        Players players = Players.fromNames(List.of("pobi", "jason"));
        players.getPlayers().forEach(Player::stand);
        
        assertThat(players.getDrawablePlayer()).isNull();
    }
}
