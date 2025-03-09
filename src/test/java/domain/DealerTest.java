package domain;

import static org.assertj.core.api.Assertions.assertThat;

import constant.Suit;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {

  @Nested
  @DisplayName("딜러가 카드를 받아야 하는지 여부를 반환한다.")
  class IsPickTrumpCard {

    @DisplayName("16점 이하라면, 딜러가 카드를 뽑는 여부를 올바르게 반환한다.")
    @Test
    public void isPickCard() {
      // given
      final var dealer = new Dealer();

      // when
      final var actual = dealer.isHit();

      // then
      assertThat(actual).isTrue();
    }

    @DisplayName("16점 초과라면, 딜러가 카드를 뽑는 여부를 올바르게 반환한다.")
    @Test
    public void isPickCard1() {
      // given
      final TrumpCard card1 = new TrumpCard(Rank.TEN, Suit.CLUB);
      final TrumpCard card2 = new TrumpCard(Rank.SEVEN, Suit.HEART);
      final Hand hand = new Hand(List.of(card1, card2));
      final var dealer = new Dealer(hand);

      // when
      final var actual = dealer.isHit();

      // then
      assertThat(actual).isFalse();
    }
  }

  @Nested
  @DisplayName("딜러는 플레이어와 대결한다.")
  class StartDuel {

    @DisplayName("딜러는 플레이와의 대결 결과를 올바르게, 기록한다.")
    @Test
    void startDuel() {
      // given
      final TrumpCard card1 = new TrumpCard(Rank.TEN, Suit.CLUB);
      final TrumpCard card2 = new TrumpCard(Rank.SEVEN, Suit.HEART);
      final Hand hand = new Hand(List.of(card1, card2));
      final var dealer = new Dealer(hand);

      final Player player = new Player("loser");
      final List<TrumpCard> playerCards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB));
      final Deck playerDeck = new Deck(new ArrayDeque<>(playerCards));
      player.hit(playerDeck);

      final Player winner = new Player("w");
      final List<TrumpCard> winnerCards = List.of(new TrumpCard(Rank.ACE, Suit.CLUB),
          new TrumpCard(Rank.TEN, Suit.CLUB));
      final Deck winnerDeck = new Deck(new ArrayDeque<>(winnerCards));
      winner.hit(winnerDeck);
      winner.hit(winnerDeck);

      // when
      dealer.round(player);
      dealer.round(winner);

      // then
      assertThat(dealer.getWinCount()).isEqualTo(1);
      assertThat(dealer.getLoseCount()).isEqualTo(1);
    }

  }
}
