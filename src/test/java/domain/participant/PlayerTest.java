package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Bet;
import domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PlayerTest {

  @ParameterizedTest
  @CsvSource({"21,true", "22,false",})
  @DisplayName("딜러가 카드를 뽑을 수 있는 지 올바르게 반환한다.")
  void test_isHit(int value, boolean expected) {
    // given
    final String name = "player";
    final Bet bet = new Bet(0);
    final var player = new Player(name, bet);
    final var score = new Score(value);
    // when&then
    assertThat(player.isHit(score)).isEqualTo(expected);
  }
}
