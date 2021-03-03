package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {
  GameResult gameResult;

  @BeforeEach
  void setUp() {
    int dealerScore = 10;
    Map<String, Integer> gamerScore = new HashMap<>();

    gamerScore.put("nabom", 15);
    gamerScore.put("neozal", 8);

    gameResult = new GameResult(
        dealerScore, gamerScore
    );
  }

  @Test
  @DisplayName("게이머의 최종 결과를 반환한다.")
  void getGamerResult() {
    Map<String, Boolean> gamerResult = gameResult.getGamerResult();

    assertThat(gamerResult.get("nabom")).isTrue();
    assertThat(gamerResult.get("neozal")).isFalse();
  }

  @Test
  @DisplayName("딜러의 최종 결과를 반환한다.")
  void getDealerResult() {
    List<Boolean> dealerResult = gameResult.getDealerResult();
    Map<Boolean, Long> collect = dealerResult.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    assertThat(collect.get(true)).isEqualTo(1);
    assertThat(collect.get(false)).isEqualTo(1);
  }
}
