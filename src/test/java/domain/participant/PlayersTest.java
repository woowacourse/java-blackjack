package domain.participant;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class PlayersTest {

    @Test
    @DisplayName("플레이어 인원 수는 5명 이하여야 합니다.")
    void 플레이어인원수_5명이하_성공() {
        // given
        List<Player> players = List.of(new Player("pobi"), new Player("james"));

        // when - then
        Assertions.assertDoesNotThrow(() -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 인원 수는 6명 이상인 경우 오류가 발생해야 한다.")
    void 플레이어인원수_6명이상_오류() {
        // given
        List<Player> players = List.of(
                new Player("pobi")
                , new Player("james")
                , new Player("eunoh")
                , new Player("ruro")
                , new Player("rama")
                , new Player("dudu"));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어 이름이 중복되는 경우 오류가 발생해야 한다.")
    void 플레이어_이름_중복_금지() {
        // given
        List<Player> players = List.of(
                new Player("pobi")
                , new Player("james")
                , new Player("pobi"));

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Players(players));
    }

    @Test
    @DisplayName("플레이어를 정확하게 찾는지 검증한다.")
    void findByTest() {
        // given
        Player targetPlayer = new Player("pobi");
        List<Player> playerNames = List.of(
                targetPlayer
                , new Player("james"));

        // when - then
        Players players = new Players(playerNames);

        Assertions.assertEquals(players.findBy(targetPlayer), targetPlayer);
    }

    @ParameterizedTest
    @DisplayName("딜러의 패가 주어졌을 때, 플레이어가 자신의 승패를 계산한다.")
    @MethodSource("provideGameResultCases")
    void calculateResultParameterizedTest(String description,
                                          List<Card> dealerCards,
                                          List<Card> playerCards,
                                          MatchResult expected) {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        Players players = new Players(List.of(player));

        // when
        dealerCards.forEach(dealer::receive);
        playerCards.forEach(players.findBy(player)::receive);

        // then
        for (Map.Entry<Player, MatchResult> entry : players.calculateMatchResult(dealer).entrySet()) {
            Assertions.assertEquals(expected, entry.getValue());
        }
    }

    private static Stream<Arguments> provideGameResultCases() {
        return Stream.of(
                Arguments.of("플레이어만 버스트 패",
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART),
                                new Card(Rank.JACK, Suit.HEART)),
                        MatchResult.LOSE),
                Arguments.of("딜러만 버스트 플레이어 승",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART),
                                new Card(Rank.JACK, Suit.HEART)),
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.WIN),
                Arguments.of("플레이어가 딜러를 이긴 경우 승리",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART)),
                        List.of(new Card(Rank.QUEEN, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.WIN),
                Arguments.of("플레이어가 딜러와 비긴 경우 무승부",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART)),
                        List.of(new Card(Rank.SEVEN, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.DRAW),
                Arguments.of("동점, 플레이어만 블랙잭 승리",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.ACE, Suit.HEART),
                                new Card(Rank.KING, Suit.HEART)),
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.WIN),
                Arguments.of("동점, 딜러만 블랙잭 플레이어 패배",
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.ACE, Suit.HEART),
                                new Card(Rank.KING, Suit.HEART)),
                        MatchResult.LOSE),
                Arguments.of("딜러와 플레이어 모두 블랙잭 무승부",
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.ACE, Suit.HEART)),
                        MatchResult.DRAW),
                Arguments.of("플레이어가 딜러에게 진 경우 패배",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART)),
                        MatchResult.LOSE)
        );
    }
}
