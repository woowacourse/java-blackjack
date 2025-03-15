package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.Bet;
import domain.card.Deck;
import exceptions.BlackjackArgumentException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

  @Nested
  @DisplayName("참가자 생성 테스트")
  class GenerateParticipantsTest {

    @Test
    @DisplayName("참가자가 없다면 예외를 발생시킨다.")
    void error_whenNoDealer() {
      //given
      final List<Participant<Player>> given = new ArrayList<>();
      final var dealer = new Participant<>(new Dealer(new Bet(0)));
      //then
      assertThatThrownBy(() -> new Participants(dealer, given))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("게임 참가자가 없습니다! 게임 설정을 다시 진행해주세요.");
    }

    @Test
    @DisplayName("참가자가 중복이면, 예외가 발생한다.")
    void error_whenDuplicatePlayer() {
      //given
      final Bet bet = new Bet(0);
      final var dealer = new Participant<>(new Dealer(bet));
      final List<Participant<Player>> given = new ArrayList<>();
      final Player duplicate = new Player("duplicate", bet);
      given.add(new Participant<>(duplicate));
      given.add(new Participant<>(duplicate));
      //then
      assertThatThrownBy(() -> new Participants(dealer, given))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("중복된 닉네임을 가진 플레이어가 포함되어있습니다");
    }

    @Test
    @DisplayName("딜러가 없으면, 예외를 발생시킨다.")
    void error_whenEmptyDealer() {
      //given
      final Bet bet = new Bet(0);
      final List<Participant<Player>> players = new ArrayList<>();
      final Player player = new Player("given", bet);
      players.add(new Participant<>(player));
      //then
      assertThatThrownBy(() -> new Participants(null, players))
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("딜러를 찾을 수 없습니다. 딜러는 반드시 게임에 참가해야 합니다.");
    }

    @Test
    @DisplayName("딜을 시작할 때, 참가자를 올바르게 생성한다.")
    void test_initialDealOf() {
      //given
      final String name = "Test";
      final Bet value = new Bet(1000);
      final Map<String, Bet> given = new HashMap<>();
      given.put(name, value);
      final var expect = new Player(name, value);
      //when
      final var participants = Participants.initialDealOf(given, Deck.createShuffledDecks(1));
      //then
      final var actualDealer = participants.getDealer();
      assertThat(actualDealer.getCards().size()).isEqualTo(2);

      final var actualPlayer = participants.getPlayers().getFirst();
      assertThat(actualPlayer.getName()).isEqualTo(expect.getName());
      assertThat(actualPlayer.getCards().size()).isEqualTo(2);
    }
  }

  @Nested
  @DisplayName("전체 참가자 반환 테스트")
  class ParticipantFindAndGetTest {

    @Test
    @DisplayName("딜러와 플레이어를 모두 반환한다.")
    void test_getAllParticipants() {
      //given
      final String name = "Test";
      final Bet value = new Bet(1000);
      final Map<String, Bet> given = new HashMap<>();
      given.put(name, value);

      //when
      final var participants = Participants.initialDealOf(given, Deck.createShuffledDecks(1));
      //then
      final var actual = participants.getAllParticipants();
      assertThat(actual.size()).isEqualTo(2);
      assertThat(actual.getFirst().getName()).isEqualTo(DealerRoster.DEFAULT.getName());
      assertThat(actual.getLast().getName()).isEqualTo(name);
    }
  }
}
