package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Player;
import domain.participant.Players;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어가 5명 초과면 예외를 발생시킨다")
    @Test
    void test2() {
        //given
        Player player1 = Player.init(new Nickname("짱구"));
        Player player2 = Player.init(new Nickname("밍곰"));
        Player player3 = Player.init(new Nickname("포비"));
        Player player4 = Player.init(new Nickname("네오"));
        Player player5 = Player.init(new Nickname("가이온"));
        Player player6 = Player.init(new Nickname("플린트"));
        Set<Player> allPlayer = Set.of(player1, player2, player3, player4, player5, player6);
        //when & then
        assertThatThrownBy(() -> Players.withoutBetting(allPlayer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 최대 5명입니다.");
    }
}
