package gameResult;

import card.AceRank;
import card.Card;
import card.NormalRank;
import card.Suit;
import result.MatchResultType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import participant.Dealer;
import participant.Player;
import util.TestCardDistributor;

import java.util.List;
import java.util.stream.Stream;

public class MatchResultTypeTest {

    @DisplayName("올바른 MatchResult 를 반환하는 지")
    @ParameterizedTest
    @MethodSource("createMatchResultTestData")
    void matchResultTest(List<Card> playerCards, List<Card> dealerCards, MatchResultType expectedMatchResult) {
        //given
        Player player = new Player("hippo");
        TestCardDistributor.divideCardToPlayer(playerCards, player);

        Dealer dealer = new Dealer();
        TestCardDistributor.divideCardToDealer(dealerCards, dealer);
        //when
        //then
        Assertions.assertThat(MatchResultType.of(player, dealer)).isEqualTo(expectedMatchResult);

    }

    private static Stream<Arguments> createMatchResultTestData() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                            new Card(Suit.DIAMONDS, NormalRank.FIVE),
                            new Card(Suit.DIAMONDS, NormalRank.FIVE)
                        ),
                        List.of(
                                new Card(Suit.DIAMONDS, NormalRank.FIVE),
                                new Card(Suit.DIAMONDS, NormalRank.FIVE)
                        ),
                        MatchResultType.DRAW
                ),
                Arguments.arguments(
                        List.of(
                                new Card(Suit.DIAMONDS, AceRank.SOFT_ACE),
                                new Card(Suit.DIAMONDS, NormalRank.KING)
                        ),
                        List.of(
                                new Card(Suit.DIAMONDS, NormalRank.FIVE),
                                new Card(Suit.DIAMONDS, NormalRank.FIVE)
                        ),
                        MatchResultType.WIN
                ),
                Arguments.arguments(
                        List.of(
                                new Card(Suit.DIAMONDS, NormalRank.FIVE),
                                new Card(Suit.DIAMONDS, NormalRank.FIVE)
                        ),
                        List.of(
                                new Card(Suit.DIAMONDS, NormalRank.TEN),
                                new Card(Suit.DIAMONDS, NormalRank.FIVE)
                        ),
                        MatchResultType.LOSE
                )
        );
    }

    @DisplayName("반대되는 MatchResult 반환하는 지")
    @ParameterizedTest
    @MethodSource("makeMatchResultAndReverseResult")
    void calculateReverseResultTest(MatchResultType result, MatchResultType reverseResult) {
        //given
        MatchResultType matchResultType = result.calculateReverseResult();
        //when
        //then
        Assertions.assertThat(matchResultType).isEqualTo(reverseResult);
    }

    private static Stream<Arguments> makeMatchResultAndReverseResult() {
        return Stream.of(
                Arguments.arguments(
                        MatchResultType.WIN,
                        MatchResultType.LOSE
                ),
                Arguments.arguments(
                        MatchResultType.LOSE,
                        MatchResultType.WIN
                ),
                Arguments.arguments(
                        MatchResultType.DRAW,
                        MatchResultType.DRAW
                )
        );
    }
}
