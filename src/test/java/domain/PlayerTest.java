package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

  @Nested
  @DisplayName("플레아어가 카드를 뽑을지 여부를 반환한다.")
  class isPickTrumpCard {

    @DisplayName("플레이어가 카드를 뽑을지 여부를 올바르게 반환한다.")
    @ParameterizedTest
    @MethodSource("providePlayerHand")
    public void isPickCard(final List<TrumpCard> cards, final boolean expected) {
      // given
      final Hand hand = new Hand(cards);
      final Participant participant = new Participant(hand);
      final Player player = new Player("Player", participant);

      // when
      final boolean actual = player.isHit();

      // then
      assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePlayerHand() {
      return Stream.of(
          Arguments.of(
              List.of(new TrumpCard(Rank.TEN, Suit.CLUB), new TrumpCard(Rank.TEN, Suit.DIAMOND),
                  new TrumpCard(Rank.ACE, Suit.CLUB)), true),
          Arguments.of(
              List.of(new TrumpCard(Rank.TEN, Suit.DIAMOND),
                  new TrumpCard(Rank.TEN, Suit.CLUB), new TrumpCard(Rank.TWO, Suit.CLUB)), false)
      );
    }

  }

  @Nested
  @DisplayName("결투를 진행한다.")
  class Duel {

    @DisplayName("올바르게 승자를 가려낸다.")
    @Test
    public void duel() {
      // given
      final Player winner = new Player("Winner");
      final List<TrumpCard> winnerCards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB));
      final Deck winnerDeck = new Deck(new ArrayDeque<>(winnerCards));
      winner.hit(winnerDeck);

      final Player loser = new Player("Loser");
      final List<TrumpCard> loserCards = List.of(new TrumpCard(Rank.NINE, Suit.CLUB));
      final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
      loser.hit(loserDeck);

      final Dealer dealer = new Dealer();
      final List<TrumpCard> dealerCards = List.of(new TrumpCard(Rank.TEN, Suit.CLUB));
      final Deck dealerDeck = new Deck(new ArrayDeque<>(dealerCards));
      dealer.hit(dealerDeck);

      // when
      winner.round(dealer.getParticipant());
      loser.round(dealer.getParticipant());

      // then
      assertThat(winner.isWinDuel()).isTrue();
      assertThat(loser.isWinDuel()).isFalse();
    }

    @DisplayName("21점이 넘는다면, 상대방이 승리한다.")
    @Test
    public void duelOverThan() {
      // given
      final Player loser = new Player("Loser");
      final List<TrumpCard> loserCards = List.of(
          new TrumpCard(Rank.TEN, Suit.SPADE), new TrumpCard(Rank.TEN, Suit.CLUB),
          new TrumpCard(Rank.TWO, Suit.SPADE));
      final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
      loser.hit(loserDeck);
      loser.hit(loserDeck);
      loser.hit(loserDeck);

      final Dealer dealer = new Dealer();
      final List<TrumpCard> dealerCards = List.of(new TrumpCard(Rank.TEN, Suit.CLUB));
      final Deck dealerDeck = new Deck(new ArrayDeque<>(dealerCards));
      dealer.hit(dealerDeck);

      // when
      loser.round(dealer.getParticipant());

      // then
      assertThat(loser.isWinDuel()).isFalse();
    }

    @DisplayName("상대방이 21점이 넘고 내가 21점 이하라면, 내가 승리한다.")
    @Test
    public void duelOverThanX() {
      // given
      final Player loser = new Player("Loser");
      final List<TrumpCard> loserCards = List.of(
          new TrumpCard(Rank.TEN, Suit.SPADE));
      final Deck loserDeck = new Deck(new ArrayDeque<>(loserCards));
      loser.hit(loserDeck);

      final Dealer dealer = new Dealer();
      final List<TrumpCard> dealerCards = List.of(
          new TrumpCard(Rank.TEN, Suit.SPADE), new TrumpCard(Rank.TEN, Suit.CLUB),
          new TrumpCard(Rank.TWO, Suit.SPADE));
      final Deck dealerDeck = new Deck(new ArrayDeque<>(dealerCards));
      dealer.hit(dealerDeck);
      dealer.hit(dealerDeck);
      dealer.hit(dealerDeck);

      // when
      loser.round(dealer.getParticipant());

      // then
      assertThat(loser.isWinDuel()).isTrue();
    }
  }
}
