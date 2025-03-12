package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Suit;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.TrumpCard;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {

  @Nested
  @DisplayName("참가자 카드 뽑기")
  class HitTest {

    @Test
    @DisplayName("참가자는 한 장의 카드를 뽑는다.")
    void test_hit() {
      // given
      final var participant = new MockParticipant();
      final var card = new TrumpCard(Rank.ACE, Suit.CLUB);

      // when
      participant.hit(card);

      // then
      List<TrumpCard> actualCards = participant.getCards();
      assertThat(actualCards.getFirst()).isEqualTo(card);
    }

    @Test
    @DisplayName("참가자는 딜을 시작할 때, 덱으로부터 올바르게 카드를 뽑는다.")
    void test_initialDeal() {
      // given
      final Participant participant = new MockParticipant();
      final List<TrumpCard> cards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB),
          new TrumpCard(Rank.ACE, Suit.DIAMOND));
      final Deck deck = new Deck(new ArrayDeque<>(cards));

      // when
      participant.initialDeal(deck);

      // then
      List<TrumpCard> actualCards = participant.getCards();
      assertThat(actualCards).isEqualTo(cards);
      assertThat(actualCards).isNotSameAs(cards); // 방어적 복사 테스트
    }
  }

  @Nested
  @DisplayName("핸드 랭크 총합 반환")
  class calculateScore {

    @Test
    @DisplayName("올바른 핸드 랭크의 총합을 반환한다.")
    void test_calculateScore() {
      // given
      final Participant participant = new MockParticipant();
      final List<TrumpCard> cards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB),
          new TrumpCard(Rank.ACE, Suit.DIAMOND));
      final Deck deck = new Deck(new ArrayDeque<>(cards));

      // when
      participant.initialDeal(deck);

      // then
      assertThat(participant.calculateScore()).isEqualTo(12);
    }
  }


  @Nested
  @DisplayName("라운드 결과를 반환한다.")
  class Round {

    @Test
    @DisplayName("올바르게 승자를 가려낸다.")
    void test_round() {
      // given
      final Participant winner = new MockParticipant();
      winner.hit(new TrumpCard(Rank.ACE, Suit.CLUB));

      final Participant loser = new MockParticipant();
      loser.hit(new TrumpCard(Rank.TEN, Suit.CLUB));
      // when&then
      assertThat(winner.round(loser)).isTrue();
      assertThat(loser.round(winner)).isFalse();
    }

    @Test
    @DisplayName("21점이 넘는다면, 상대방이 승리한다.")
    void test_RoundOverBurst() {
      // given
      final Participant buster = new MockParticipant();
      buster.hit(new TrumpCard(Rank.TEN, Suit.CLUB));
      buster.hit(new TrumpCard(Rank.TEN, Suit.CLUB));
      buster.hit(new TrumpCard(Rank.TEN, Suit.CLUB));

      final Participant player = new MockParticipant();
      player.hit(new TrumpCard(Rank.TEN, Suit.CLUB));
      // when&then
      assertThat(player.round(buster)).isTrue();
      assertThat(buster.round(player)).isFalse();
    }
  }

  private static class MockParticipant extends Participant {

    public MockParticipant() {
      super();
    }

    @Override
    public boolean isHit() {
      return false;
    }

    @Override
    public boolean isDealer() {
      return false;
    }

    @Override
    public String getName() {
      return "MOCK";
    }
  }
}
