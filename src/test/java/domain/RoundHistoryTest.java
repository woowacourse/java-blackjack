package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Rank;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mock.MockParticipant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RoundHistoryTest {

  @Nested
  @DisplayName("라운드 기록이 올바르게 생성된다.")
  class GenerateRound {

    @Test
    @DisplayName("라운드 기록이 올바르게 생성된다.")
    void test_generateRound() {
      //given
      final Participant dealer = new Dealer();
      dealer.hit(new TrumpCard(Rank.TWO, Suit.CLUB));
      String name = "player";
      final Participant player = new Player(name);
      player.hit(new TrumpCard(Rank.ACE, Suit.CLUB));
      final List<Participant> players = new ArrayList<>(List.of(player));

      //when
      final var roundHistory = RoundHistory.of(dealer, players);
      //then
      Map<String, RoundResult> expected = new HashMap<>(Map.of(name, RoundResult.WIN));
      assertThat(roundHistory.getHistory()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 라운드 기록이 올바르게 생성된다.")
    void test_getDealerResult() {
      //given
      final Participant dealer = new Dealer();
      dealer.hit(new TrumpCard(Rank.TWO, Suit.CLUB));
      String name = "player";
      final Participant player = new Player(name);
      player.hit(new TrumpCard(Rank.ACE, Suit.CLUB));
      final List<Participant> players = new ArrayList<>(List.of(player));

      //when
      final var roundHistory = RoundHistory.of(dealer, players);
      //then
      Map<RoundResult, Integer> expected = new HashMap<>(Map.of(RoundResult.WIN, 1));
      assertThat(roundHistory.getDealerResult()).isEqualTo(expected);
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
        assertThat(RoundResult.round(winner, loser)).isEqualTo(RoundResult.WIN);
        assertThat(RoundResult.round(loser, winner)).isEqualTo(RoundResult.LOSE);
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
        assertThat(RoundResult.round(player, buster)).isEqualTo(RoundResult.WIN);
        assertThat(RoundResult.round(buster, player)).isEqualTo(RoundResult.LOSE);
      }
    }
  }
}
