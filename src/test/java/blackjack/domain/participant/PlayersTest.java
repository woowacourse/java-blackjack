package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @Test
    @DisplayName("중복된 이름을 가진 플레이어들로 객체를 생성하면 예외가 발생한다.")
    void createPlayers_ThrowException_WhenNameIsDuplicate() {
        List<String> names = List.of("pobi", "pobi");
        List<Integer> bettingAmounts = List.of(10000, 20000);
        
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Players.fromNameAndBettingAmounts(names, bettingAmounts))
                .withMessageContaining("중복될 수 없습니다");
    }
    
    @Test
    @DisplayName("카드를 뽑을 수 있는 첫 번째 플레이어를 반환한다.")
    void getDrawablePlayer_ReturnPlayer_WhenDrawablePlayerExists() {
        List<String> names = List.of("pobi", "jason");
        List<Integer> bettingAmounts = List.of(10000, 20000);
        
        Players players = Players.fromNameAndBettingAmounts(names, bettingAmounts);
        
        players.getPlayers().getFirst().stand();
        
        Player drawablePlayer = players.getDrawablePlayer();
        assertThat(drawablePlayer.getNickname()).isEqualTo("jason");
    }
    
    @Test
    @DisplayName("모든 플레이어가 카드를 뽑을 수 없는 상태이면 null을 반환한다.")
    void getDrawablePlayer_ReturnNull_WhenAllPlayersAreNotDrawable() {
        List<String> names = List.of("pobi", "jason");
        List<Integer> bettingAmounts = List.of(10000, 20000);
        Players players = Players.fromNameAndBettingAmounts(names, bettingAmounts);
        players.getPlayers().forEach(Player::stand);
        
        assertThat(players.getDrawablePlayer()).isNull();
    }
}
