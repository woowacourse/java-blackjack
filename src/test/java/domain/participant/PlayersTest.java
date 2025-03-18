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
        Player player1 = Player.init("포비", Money.of(100000));
        Player player2 = Player.init("포비", Money.of(100000));
        //when & then
        assertThatThrownBy(() -> Players.of(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 플레이어가 있습니다.");
    }

    @DisplayName("플레이어가 5명 초과면 예외를 발생시킨다")
    @Test
    void test2() {
        //given
        Player player1 = Player.init("짱구", Money.of(100000));
        Player player2 = Player.init("밍곰", Money.of(100000));
        Player player3 = Player.init("포비", Money.of(100000));
        Player player4 = Player.init("네오", Money.of(100000));
        Player player5 = Player.init("가이온", Money.of(100000));
        Player player6 = Player.init("플린트", Money.of(100000));
        List<Player> allPlayer = List.of(player1, player2, player3, player4, player5, player6);
        //when & then
        assertThatThrownBy(() -> Players.of(allPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최대 5명입니다.");
    }
}