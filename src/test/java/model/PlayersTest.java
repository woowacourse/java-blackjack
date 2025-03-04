package model;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

  @Test
  @DisplayName("여러명의_플레이어가_생성됐는지_확인")
  void newPlayers() {
    // given
    List<Player> actual = List.of(
        new Player("pobi"),
        new Player("hippo")
    );
    // when
    Players players = new Players(actual);
    // then
    assertAll(
        () -> Assertions.assertThat(players.getPlayers().size()).isEqualTo(2),
        () -> Assertions.assertThat(players.getPlayers()).containsAll(actual)
    );
  }
}
