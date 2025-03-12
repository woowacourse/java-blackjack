package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import domain.card.Rank;
import domain.card.Score;
import domain.card.Suit;
import domain.card.TrumpCard;
import java.util.ArrayDeque;
import java.util.List;
import mock.MockParticipant;
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
      final Score score = participant.calculateScore();
      assertThat(score.value()).isEqualTo(12);
    }
  }
}
