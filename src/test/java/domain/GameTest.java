package domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    @DisplayName("플레이어 이름이 중복된 경우 예외가 발생한다.")
    @Test
    void 플레이어_이름이_중복된_경우_예외가_발생한다() {
        List<String> duplicatedNames = List.of("피즈", "피즈", "스타크");

        Assertions.assertThatThrownBy(() -> new Game(duplicatedNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("모든 플레이어가 정상적으로 생성된다.")
    @Test
    public void 모든_플레이어가_정상적으로_생성된다() {
        List<String> names = List.of("피즈", "스타크");

        //given
        Game game = new Game(names);
        //when
        Set<Player> players = game.getPlayers();
        Set<String> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());
        //then
        Assertions.assertThat(playerNames).contains("피즈");
        Assertions.assertThat(playerNames).contains("스타크");
    }
}