package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Bet;
import domain.card.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DealerTest {

  @ParameterizedTest
  @CsvSource({"16,true", "17,false",})
  @DisplayName("딜러가 카드를 뽑을 수 있는 지 올바르게 반환한다.")
  void test_isHit(int value, boolean expected) {
    // given
    final Bet bet = new Bet(0);
    final var dealer = new Dealer(bet);
    final var score = new Score(value);
    // when&then
    assertThat(dealer.isHit(score)).isEqualTo(expected);
  }

  @Test
  @DisplayName("기본으로 생성된 딜러의 이름은 상수로 관리한다.")
  void test_nameIsConstant() {
    //given
    final var dealer = new Dealer(new Bet(0));

    //when&then
    assertThat(dealer.getName()).isEqualTo(DealerRoster.DEFAULT.getName());
  }
}
