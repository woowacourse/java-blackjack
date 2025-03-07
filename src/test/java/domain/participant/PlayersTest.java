package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("중복된 플레이어가 있으면 예외를 발생시킨다")
    @Test
    void test1() {
        //given
        Player player1 = Player.init("포비");
        Player player2 = Player.init("포비");
        //when & then
        assertThatThrownBy(() -> new Players(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 플레이어가 있습니다.");
    }
}