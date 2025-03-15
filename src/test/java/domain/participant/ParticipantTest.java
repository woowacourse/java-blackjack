package domain.participant;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import domain.card.Rank;
import domain.card.Score;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.stub.StubRole;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {

  @Nested
  @DisplayName("제너릭 선언을 올바르게 사용한다")
  class UsingGenericRoleTest {

    @Test
    @DisplayName("제너릭 선언된 객체에게 올바르게 요청한다")
    void test_usingGenericRole() {
      // given
      final StubRole stubRole = new StubRole();
      final Participant<StubRole> participant = new Participant<>(stubRole);

      // when&then
      assertThat(participant.isHit()).isEqualTo(stubRole.isHit(new Score(0)));
      assertThat(participant.getName()).isEqualTo(stubRole.getName());
      assertThat(participant.getBet()).isEqualTo(stubRole.getBet());
    }

    @Test
    @DisplayName("카드를 저장할때, 히트 가능 여부를 판단하고, 불변 객체를 반환한다.")
    void test_hit() {
      // given
      final StubRole stubRole = new StubRole();
      final Participant<StubRole> participant = new Participant<>(stubRole);
      final TrumpCard card = new TrumpCard(Rank.TWO, Suit.DIAMOND);
      //when
      final Participant<? extends Role> actual = participant.hit(card);
      //then
      assertThat(actual.getCards()).isEqualTo(List.of(card));
      assertThat(actual).isNotSameAs(participant);
    }

    @Test
    @DisplayName("내장된 Role 객체가 같으면, 동등한 객체로 판단(Equals)한다.")
    void test_equals() {
      // given
      final StubRole stubRole = new StubRole();
      final Participant<StubRole> participant = new Participant<>(stubRole);
      final TrumpCard card = new TrumpCard(Rank.TWO, Suit.DIAMOND);
      //when
      final Participant<? extends Role> actual = participant.hit(card);
      //then
      assertThat(actual).isEqualTo(participant);
      assertThat(actual).isNotSameAs(participant);
    }
  }
}
