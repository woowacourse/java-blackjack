package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

  @Nested
  @DisplayName("핸드가 불변 객체인지 테스트")
  class HandImmutabilityTest {

    @Test
    @DisplayName("핸드는 카드 목록을 방어적 복사로 관리한다.")
    void test_immutability() {
      //given
      final List<TrumpCard> trumpCards = List.of(new TrumpCard(Rank.ACE, Suit.DIAMOND));
      final var cards = new ArrayList<>(trumpCards);
      final var hand = new Hand(cards);
      //when&then
      Assertions.assertThat(hand.getCards()).isEqualTo(trumpCards);
      Assertions.assertThat(hand.getCards()).isNotSameAs(trumpCards);
    }
  }

  @Nested
  @DisplayName("모든 Card의 점수 합을 계산한다.")
  class CalculateAllScore {

    @ParameterizedTest
    @MethodSource("getCardList")
    @DisplayName("모든 Card의 점수 합을 올바르게 계산한다.")
    void test_calculateAllScore(final List<TrumpCard> cards, final int expected) {
      //given
      final var cardHand = new Hand(cards);

      //when&then
      final Score score = cardHand.calculateScore();

      Assertions.assertThat(score.value()).isEqualTo(expected);
    }

    private static Stream<Arguments> getCardList() {
      return Stream.of(
          Arguments.of(
              List.of(new TrumpCard(Rank.NINE, Suit.CLUB), new TrumpCard(Rank.NINE, Suit.HEART)),
              18),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.ACE, Suit.HEART)),
              12),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.TEN, Suit.HEART)),
              21),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.ACE, Suit.HEART),
                  new TrumpCard(Rank.TEN, Suit.HEART)), 12),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.ACE, Suit.HEART),
                  new TrumpCard(Rank.TEN, Suit.CLUB), new TrumpCard(Rank.TEN, Suit.HEART)), 22),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.ACE, Suit.HEART),
                  new TrumpCard(Rank.ACE, Suit.SPADE)), 13),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.ACE, Suit.HEART),
                  new TrumpCard(Rank.ACE, Suit.SPADE), new TrumpCard(Rank.ACE, Suit.DIAMOND)), 14)
      );
    }
  }
}
