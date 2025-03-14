package domain;

import domain.participant.Role;
import domain.stub.StubRole;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BetTest {

  @Nested
  @DisplayName("배팅금에 대한 배당금 총합의 차를 반환한다.")
  class AllocationTotalDifferenceTest {

    @Test
    @DisplayName("배팅금에 대한 배당금 총합의 차를 반환한다.")
    void test_seekAllocationTotalDifference() {
      //given
      final var bet = new Bet(1000);
      final List<Role> roles = new ArrayList<>(List.of(new StubRole()));
      //when
      final var actual = bet.seekAllocationTotalDifference(roles);
      //then
      Assertions.assertThat(actual).isEqualTo(new Bet(0));
    }
  }
}
