package domain;

import domain.card.Rank;
import domain.card.Suit;
import domain.card.TrumpCard;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RoundResultTest {

  @Nested
  @DisplayName("Round 결과 테스트")
  class RoundTest {

    @ParameterizedTest
    @MethodSource("getRoundTestGiven")
    @DisplayName("딜러와 플레이어 간 라운드 결과를 올바르게 반환한다.")
    void test_round(
        List<TrumpCard> playerCards,
        List<TrumpCard> dealerCards,
        RoundResult expect
    ) {
      //given
      final var bet = new Bet(1000);
      final var roleForPlayer = new Player("name", bet);
      final var roleForDealer = new Dealer(bet);

      final Participant player = new Participant(roleForPlayer, playerCards);
      final Participant dealer = new Participant(roleForDealer, dealerCards);
      //when
      final var actual = RoundResult.round(player, dealer);
      //then
      Assertions.assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> getRoundTestGiven() {
      return Stream.of(
          Arguments.of(
              List.of(new TrumpCard(Rank.NINE, Suit.CLUB), new TrumpCard(Rank.NINE, Suit.HEART)),
              List.of(new TrumpCard(Rank.TWO, Suit.CLUB), new TrumpCard(Rank.TWO, Suit.HEART)),
              RoundResult.WIN),
          Arguments.of(
              List.of(new TrumpCard(Rank.TWO, Suit.CLUB), new TrumpCard(Rank.TWO, Suit.HEART)),
              List.of(new TrumpCard(Rank.NINE, Suit.CLUB), new TrumpCard(Rank.NINE, Suit.HEART)),
              RoundResult.LOSE),
          Arguments.of(
              List.of(new TrumpCard(Rank.NINE, Suit.CLUB), new TrumpCard(Rank.NINE, Suit.HEART)),
              List.of(new TrumpCard(Rank.NINE, Suit.CLUB), new TrumpCard(Rank.NINE, Suit.HEART)),
              RoundResult.PUSH),
          Arguments.of(
              List.of(new TrumpCard(Rank.ACE, Suit.CLUB), new TrumpCard(Rank.JACK, Suit.HEART)),
              List.of(new TrumpCard(Rank.NINE, Suit.CLUB), new TrumpCard(Rank.NINE, Suit.HEART)),
              RoundResult.BLACKJACK)

      );
    }
  }
}
