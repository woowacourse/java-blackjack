package domain.match;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchResultTest {

    @ParameterizedTest
    @DisplayName("딜러와 플레이어 패에 따라 승패를 계산한다.")
    @MethodSource("provideGameResultCases")
    void judge(String description,
               List<Card> dealerCards,
               List<Card> playerCards,
               MatchResult expected) {

        // given
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");

        dealerCards.forEach(dealer::receive);
        playerCards.forEach(player::receive);

        // when
        MatchResult result = MatchResult.judge(player, dealer);

        // then
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideGameResultCases() {
        return Stream.of(
                Arguments.of("플레이어 버스트 패",
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART),
                                new Card(Rank.JACK, Suit.HEART)),
                        MatchResult.LOSE
                ),
                Arguments.of("딜러만 버스트 플레이어 승",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART),
                                new Card(Rank.JACK, Suit.HEART)),
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.WIN
                ),
                Arguments.of("플레이어가 딜러 보다 높은 점수 플레이어 승",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART)),
                        List.of(new Card(Rank.QUEEN, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.WIN
                ),
                Arguments.of("플레이어가 딜러와 비긴 경우 무승부",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART)),
                        List.of(new Card(Rank.SEVEN, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.DRAW
                ),
                Arguments.of("동점, 플레이어만 블랙잭 플레이어 승",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.ACE, Suit.HEART),
                                new Card(Rank.KING, Suit.HEART)),
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        MatchResult.WIN
                ),
                Arguments.of("동점, 딜러만 블랙잭 딜러 승",
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.ACE, Suit.HEART),
                                new Card(Rank.KING, Suit.HEART)),
                        MatchResult.LOSE
                ),
                Arguments.of("딜러와 플레이어 모두 블랙잭 무승부",
                        List.of(new Card(Rank.ACE, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.ACE, Suit.HEART)),
                        MatchResult.DRAW
                ),
                Arguments.of("플레이어가 딜러 보다 낮은 점수 플레이어 패",
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.KING, Suit.CLOVER)),
                        List.of(new Card(Rank.JACK, Suit.CLOVER),
                                new Card(Rank.SEVEN, Suit.HEART)),
                        MatchResult.LOSE
                )
        );
    }
}
