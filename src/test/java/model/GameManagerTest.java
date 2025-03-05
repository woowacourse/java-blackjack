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
    Players players = Players.from(List.of(
        "pobi",
        "hippo"
    ));
    GameManager manager = new GameManager(Dealer.of(),players);
    //when
    //then
    for (Player player : players.getPlayers()) {
      manager.divideCardByParticipant(player, amount);
      Assertions.assertThat(player.getHands().size()).isEqualTo(amount);
    }
  }

  @Test
  @DisplayName("모든 참가자에게 2장씩 카드를 배부했는 지")
  void divideAllParticipant() {
    // given
    List<String> allPlayer = List.of(
            "pobi",
            "hippo",
            "kali"
    );
    Players players = Players.from(allPlayer);
    Dealer dealer = Dealer.of();

    GameManager gameManager = new GameManager(dealer, players);

    // when
    int amount = 2;
    gameManager.divideAllParticipant(amount);

    // then
    for (Player player : players.getPlayers()) {
      Assertions.assertThat(player.getHands().size()).isEqualTo(amount);
    }
    Assertions.assertThat(dealer.getHands().size()).isEqualTo(amount);
  }
}
