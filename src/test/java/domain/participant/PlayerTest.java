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
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PlayerTest {

    @Test
    @DisplayName("플레이어 이름은 1글자 이상 8글자 이하여야 한다.")
    void 이름은_1글자_이상_8글자_이하_성공() {
        // given
        String name = "pobi";

        // when - then
        Assertions.assertDoesNotThrow(() -> new Player(name));
    }

    @Test
    @DisplayName("플레이어 이름은 공백을 허용하지 않는다.")
    void 이름_공백_실패() {
        // given
        String name = "";

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(name));
    }

    @Test
    @DisplayName("플레이어 이름은 8글자 초과를 허용하지 않는다.")
    void 이름_8글자_초과_실패() {
        // given
        String name = "pobipobip";

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(name));
    }

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        // given
        Player player = new Player("pobi");

        // when
        player.receive(new Card(Rank.ACE, Suit.DIAMOND));

        // then
        Assertions.assertEquals(player.getCards().size(), 1);
    }

    @Test
    @DisplayName("플레이어의 승패 결과에 따라 돌려받는 수익을 계산한다.")
    void applyMatchResultToBetTest() {
        // given
        Player player = new Player("pobi");

        // when
        player.placeBet(3000);

        // then
        Assertions.assertEquals(player.applyMatchResultToBet(MatchResult.WIN), 3000);
    }


    @ParameterizedTest
    @DisplayName("딜러와 플레이어 패에 따라 승패를 계산한다.")
    @MethodSource("provideGameResultCases")
    void determineMatchResult(String description,
                              List<Card> dealerCards,
                              List<Card> playerCards,
                              MatchResult expected) {

        // given
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");

        dealerCards.forEach(dealer::receive);
        playerCards.forEach(player::receive);

        // when
        MatchResult result = player.determineMatchResultWithDealer(dealer);

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
