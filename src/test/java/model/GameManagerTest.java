package model;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GameManagerTest {

  @ParameterizedTest
  @ValueSource(ints = {1,2})
  @DisplayName("카드 덱으로부터 원하는 카드 장 수를 입력 받아 플레이어에게 분배")
  void divideCard(int amount) {
    //given
    Players players = new Players(List.of(
        Player.from("pobi"),
        Player.from("hippo")
    ));
    GameManager manager = new GameManager(Dealer.of(),players);
    //when
    //then
    for (Player player : players.getPlayers()) {
      manager.divideCardByPlayer(player, amount);
      Assertions.assertThat(player.getHands().size()).isEqualTo(amount);
    }
  }

}
