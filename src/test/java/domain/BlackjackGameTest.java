package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.TrumpCard;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

  @Nested
  @DisplayName("블랙잭 게임 생성")
  class GenerateBlackjack {

    @Test
    @DisplayName("한 명의 딜러와 명단에 따른 플레이어를 추가한다.")
    void test_initializeBlackjack() {
      //given
      final var names = List.of("pobi", "bita");
      //when
      final BlackjackGame blackjack = BlackjackGame.from(names);
      //then
      var dealer = blackjack.getDealer();
      var actualNames = blackjack.getParticipants().stream()
          .filter(participant -> !participant.isDealer())
          .map(Participant::getName)
          .toList();

      assertThat(dealer.isDealer()).isTrue();
      assertThat(actualNames).isEqualTo(names);
    }

    @Test
    @DisplayName("첫 손패를 지급할 때, 카드는 2장씩 지급한다.")
      //이때 지급되는 카드의 수는 도메인 룰에 따라 달라질 수 있다.
    void test_initialDeal() {
      //given
      final var names = List.of("pobi", "bita");
      final BlackjackGame blackjack = BlackjackGame.from(names);
      //when
      blackjack.initialDeal();
      //then
      final var participants = blackjack.getParticipants();
      var counts = participants.stream()
          .map(Participant::getHandCount)
          .distinct()
          .toList();

      assertThat(counts.size()).isEqualTo(1);
      assertThat(counts.getFirst()).isEqualTo(2);
    }
  }

  @Nested
  @DisplayName("블랙잭 카드를 올바르게 관리하라")
  class BlackjackCardManage {

    @Test
    @DisplayName("딜러는 카드를 한 장만 보여준다")
    void test_openDealerFirstHand() {
      //given
      final var names = List.of("pobi", "bita");
      final BlackjackGame blackjack = BlackjackGame.from(names);
      blackjack.initialDeal();
      final var dealer = blackjack.getDealer();
      final var dealerHand = dealer.getCards();

      //when
      final var expected = blackjack.openDealerFirstCard();

      //then
      assertThat(dealerHand).contains(expected);
    }

    @Test
    @DisplayName("참가자에게 덱에서 카드를 뽑아 전달한다")
    void test_hitByParticipant() {
      //given
      final Participant participant = new MockParticipant();
      final var card = new TrumpCard(Rank.NINE, Suit.CLUB);
      final var cards = new ArrayDeque<>(List.of(card));
      final var deck = new Deck(cards);
      final var participants = new Participants(List.of(participant));

      final var blackjack = new BlackjackGame(participants, deck);
      //when
      blackjack.hitByParticipant(participant);
      //then
      assertThat(participant.getCards().getFirst()).isEqualTo(card);
    }
  }

  private static class MockParticipant extends Participant {

    public MockParticipant() {
      super();
    }

    @Override
    public boolean isHit() {
      return true;
    }

    @Override
    public boolean isDealer() {
      return true;
    }

    @Override
    public String getName() {
      return "MOCK";
    }
  }
}
