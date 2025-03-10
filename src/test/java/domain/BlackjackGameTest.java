package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Hand;
import domain.participant.Participant;
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
      final BlackjackGame blackjack = BlackjackGame.generate();
      final var names = List.of("pobi", "bita");
      //when
      blackjack.addParticipants(names);
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
      final BlackjackGame blackjack = BlackjackGame.generate();
      final var names = List.of("pobi", "bita");
      blackjack.addParticipants(names);
      //when
      blackjack.initialDeal();
      //then
      var participants = blackjack.getParticipants();
      var counts = participants.stream()
          .map(Participant::getHand)
          .map(Hand::getCount)
          .distinct()
          .toList();

      assertThat(counts.size()).isEqualTo(1);
      assertThat(counts.getFirst()).isEqualTo(2);
    }
  }
}
