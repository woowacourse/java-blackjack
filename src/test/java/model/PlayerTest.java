package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  @DisplayName("플레이어가 생성 확인")
  void newPlayer() {
    //given
    Nickname expected = new Nickname("pobi");
    //when
    Player player = new Player("pobi");
    //then
    Assertions.assertThat(player.getNickname()).isEqualTo(expected);
  }
}
