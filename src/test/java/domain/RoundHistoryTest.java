package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import domain.card.Rank;
import domain.card.TrumpCard;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
      Map<String, Boolean> expected = new HashMap<>(Map.of(name, true));
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
      var expected = new HashMap<>(Map.of(true, 0, false, 1));
      assertThat(roundHistory.getDealerResult()).isEqualTo(expected);
    }
  }
}
