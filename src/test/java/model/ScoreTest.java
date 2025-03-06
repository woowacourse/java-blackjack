package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {
  @Test
  @DisplayName("compareTo() 올바르게 동작하는 지")
  void score() {
    Score score1 = new Score(1);
    Score score2 = new Score(3);

    Assertions.assertThat(score1.compareTo(score2)).isEqualTo(-1);
  }
}
