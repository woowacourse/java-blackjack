package gameResult;

import card.AceRank;
import card.Card;
import card.NormalRank;
import card.Suit;
import result.GameResult;
import result.MatchResultType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import participant.Dealer;
import participant.Player;
import participant.Players;
import util.TestCardDistributor;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GameResultTest {

    private final Players players = new Players(
            List.of(
                    new Player("hippo")
            )
    );
    private final Dealer dealer = new Dealer();
    private final GameResult gameResult = new GameResult();

    @DisplayName("플레이어 승패 결과가 잘 계산되는 지")
    @ParameterizedTest
    @MethodSource("createMatchResultTestData")
    void calculatePlayersMatchResultTest(List<Card> playerCards, List<Card> dealerCards, MatchResultType expectedMatchResult) {
        //given
        for (Player player : players.getPlayers()) {
            TestCardDistributor.divideCardToPlayer(playerCards, player);
        }
        TestCardDistributor.divideCardToDealer(dealerCards, dealer);
        //when
        Map<Player, MatchResultType> playerMatchResult = gameResult.calculatePlayersMatchResult(players, dealer);
        //then
        Assertions.assertThat(playerMatchResult.containsValue(expectedMatchResult)).isTrue();
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

    @DisplayName("플레이어 승패 결과가 잘 계산되는 지")
    @ParameterizedTest
    @MethodSource("createDealerMatchResultTestData")
    void calculateDealerMatchResultTest(List<Card> playerCards, List<Card> dealerCards, MatchResultType expectedMatchResult) {
        //given
        for (Player player : players.getPlayers()) {
            TestCardDistributor.divideCardToPlayer(playerCards, player);
        }
        TestCardDistributor.divideCardToDealer(dealerCards, dealer);
        Map<Player, MatchResultType> playerMatchResult = gameResult.calculatePlayersMatchResult(players, dealer);
        //when
        Map<MatchResultType, Long> dealerMatchResult = gameResult.calculateDealerMatchResult(playerMatchResult);
        //then
        Assertions.assertThat(dealerMatchResult.containsKey(expectedMatchResult)).isTrue();
        Assertions.assertThat(dealerMatchResult.get(expectedMatchResult)).isEqualTo(1);
    }

    private static Stream<Arguments> createDealerMatchResultTestData() {
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
                        MatchResultType.LOSE
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
                        MatchResultType.WIN
                )
        );
    }

}
