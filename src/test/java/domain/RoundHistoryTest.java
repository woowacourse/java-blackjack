package domain;

import domain.participant.Player;
import domain.participant.Role;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RoundHistoryTest {

  @Nested
  @DisplayName("배당 차액을 반환한다.")
  class Allocation {

    @ParameterizedTest
    @CsvSource({
        "1000,WIN,1000",
        "1000,LOSE,-1000",
        "1000,BLACKJACK,1500",
        "1000,PUSH,0"
    })
    @DisplayName("플레이어들의 배당 차액을 반환한다.")
    void test_allocatePlayers(int past, RoundResult result, int expect) {
      //given
      final Map<Role, RoundResult> history = new HashMap<>();
      final var role = new Player("Test", new Bet(past));
      history.put(role, result);

      final var roundHistory = new RoundHistory(history);
      //when
      final var actual = roundHistory.allocate();
      //then
      Assertions.assertThat(actual.getFirst().getBet()).isEqualTo(new Bet(expect));
    }
  }
}
