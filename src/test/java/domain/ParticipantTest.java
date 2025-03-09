package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantTest {

  @Nested
  @DisplayName("참가자 카드 뽑기")
  class PickTrumpCard {

    @DisplayName("참가자는 덱으로부터, 올바르게 카드를 뽑는다.")
    @Test
    public void pickCard() {
      // given
      final Participant participant = new Participant();
      final List<TrumpCard> cards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB));
      final Deck deck = new Deck(new ArrayDeque<>(cards));

      // when
      participant.pickCard(deck);

      // then
      assertThat(participant.getCards().getCards()).isNotEmpty();
    }
  }
}
