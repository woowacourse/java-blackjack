package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import domain.card.Rank;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.participant.Dealer;
import domain.participant.DealerRoster;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackManagerTest {

  @Nested
  @DisplayName("블랙잭 게임 생성")
  class GenerateBlackjack {

    @Test
    @DisplayName("딜러와 명단에 따른 플레이어를 생성한다.")
    void test_initializeBlackjack() {
      //given
      final Map<String, Money> givenParticipants = new HashMap<>();
      final var name = "name";
      givenParticipants.put(name, new Money(1000));

      //when
      final BlackjackManager blackjack = BlackjackManager.from(givenParticipants);
      //then
      var dealer = blackjack.getDealer();
      var generated = blackjack.getPlayers();
      assertThat(dealer.getName()).isEqualTo(DealerRoster.DEFAULT.getName());
      assertThat(generated.getFirst().getName()).isEqualTo(name);
    }
  }

  @Nested
  @DisplayName("딜러와 플레이어 구분없이 hit을 진행한다.")
  class BlackjackHitByParticipantTest {

    @Test
    @DisplayName("딜러에 대한 hit을 진행시킨다.")
    void test_hitByParticipantForDealer() {
      //given
      var money = new Money(1000);
      Participant<Dealer> dealer = new Participant<>(new Dealer(money));
      List<Participant<Player>> players = List.of(new Participant<>(new Player("test", money)));
      final Participants participants = new Participants(dealer, players);
      List<TrumpCard> trumpCards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB));
      final var deck = new Deck(new ArrayDeque<>(trumpCards));
      final BlackjackManager blackjackManager = new BlackjackManager(participants, deck);
      //then
      final var newDealer = blackjackManager.hitByParticipant(dealer);
      assertThat(newDealer.getCards()).isEqualTo(trumpCards);
    }

    @Test
    @DisplayName("플레이어에 대한 hit을 진행시킨다.")
    void test_hitByParticipantForPlayer() {
      //given
      var money = new Money(1000);
      Participant<Dealer> dealer = new Participant<>(new Dealer(money));
      Participant<Player> player = new Participant<>(new Player("test", money));
      List<Participant<Player>> players = List.of(player);
      final Participants participants = new Participants(dealer, players);
      List<TrumpCard> trumpCards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB));
      final var deck = new Deck(new ArrayDeque<>(trumpCards));
      final BlackjackManager blackjackManager = new BlackjackManager(participants, deck);
      //then
      final var newPlayer = blackjackManager.hitByParticipant(player);
      assertThat(newPlayer.getCards()).isEqualTo(trumpCards);
    }
  }
}
