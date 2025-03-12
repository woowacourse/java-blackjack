package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Player;
import domain.participant.Players;
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

    @DisplayName("플레이어가 5명 초과면 예외를 발생시킨다")
    @Test
    void test2() {
        //given
        Player player1 = Player.init("짱구");
        Player player2 = Player.init("밍곰");
        Player player3 = Player.init("포비");
        Player player4 = Player.init("네오");
        Player player5 = Player.init("가이온");
        Player player6 = Player.init("플린트");
        List<Player> allPlayer = List.of(player1, player2, player3, player4, player5, player6);
        //when & then
        assertThatThrownBy(() -> new Players(allPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최대 5명입니다.");
    }
}