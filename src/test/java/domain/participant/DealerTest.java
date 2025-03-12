package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Hand;
import domain.card.Rank;
import domain.card.Suit;
import domain.card.TrumpCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {

  @Nested
  @DisplayName("딜러가 카드를 받아야 하는지 여부를 반환한다.")
  class IsHit {

    @DisplayName("16점 이하라면, 카드를 뽑을 수 있는 지 올바르게 반환한다.")
    @Test
    public void test_isHit() {
      // given
      final var card1 = new TrumpCard(Rank.TEN, Suit.CLUB);
      final var card2 = new TrumpCard(Rank.SIX, Suit.HEART);
      final var hand = new Hand(List.of(card1, card2));
      final var dealer = new Dealer(hand);

      // when&then
      assertThat(dealer.isHit()).isTrue();
    }

    @DisplayName("핸드가 17이상이라면, 딜러가 카드를 뽑지 않는다.")
    @Test
    public void test_whenDealerHandStand() {
      // given
      final var card1 = new TrumpCard(Rank.TEN, Suit.CLUB);
      final var card2 = new TrumpCard(Rank.SEVEN, Suit.HEART);
      final var hand = new Hand(List.of(card1, card2));
      final var dealer = new Dealer(hand);

      // when
      final var actual = dealer.isHit();

      // then
      assertThat(actual).isFalse();
    }
  }

  @Nested
  @DisplayName("딜러는 본인이 딜러임을 반환한다.")
  class IsDealer {

    @Test
    @DisplayName("기본으로 생성된 딜러의 이름은 상수로 관리한다.")
    void test_nameIsConstant() {
      //given
      final var dealer = new Dealer();

      //when&then
      assertThat(dealer.getName()).isEqualTo(DealerRoster.DEFAULT.getName());
    }

    @Test
    @DisplayName("딜러는 딜러인지 여부를 반환한다.")
    void test_isDealer() {
      //given
      final var dealer = new Dealer();

      //when&then
      assertThat(dealer.isDealer()).isTrue();
    }
  }
}
