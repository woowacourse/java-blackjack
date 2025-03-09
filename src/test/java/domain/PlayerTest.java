package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Nested
  @DisplayName("플레이어가 카드를 받아야 하는지 여부를 반환한다.")
  class IsHit {

    @DisplayName("21점 이하라면, 카드를 뽑을 수 있는 지 올바르게 반환한다.")
    @Test
    public void test_isHit() {
      // given
      final var card1 = new TrumpCard(Rank.ACE, Suit.CLUB);
      final var card2 = new TrumpCard(Rank.TEN, Suit.HEART);
      final var hand = new Hand(List.of(card1, card2));
      final var player = new Player("player", hand);

      // when&then
      assertThat(player.isHit()).isTrue();
    }

    @DisplayName("핸드가 21점 초과라면, 딜러가 카드를 뽑지 않는다.")
    @Test
    public void test_whenPlayerBurst() {
      // given
      final var card1 = new TrumpCard(Rank.TEN, Suit.CLUB);
      final var card2 = new TrumpCard(Rank.TEN, Suit.HEART);
      final var card3 = new TrumpCard(Rank.TEN, Suit.DIAMOND);
      final var hand = new Hand(List.of(card1, card2, card3));
      final var player = new Player("player", hand);

      // when
      final var actual = player.isHit();

      // then
      assertThat(actual).isFalse();
    }
  }

  @Nested
  @DisplayName("플레이어는 본인이 플레이어임을 반환한다.")
  class IsPlayer {

    @Test
    @DisplayName("플레이어의 이름은 생성할 때 저장된다.")
    void test_nameIsConstant() {
      //given
      var name = "player";
      final var player = new Player(name);

      //when&then
      assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("딜러는 딜러인지 여부를 반환한다.")
    void test_isDealer() {
      //given
      var name = "player";
      final var player = new Player(name);

      //when&then
      assertThat(player.isDealer()).isFalse();
    }
  }
}
