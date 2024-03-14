package blackjack.model.result;

import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Score;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchResultTest {
    @ParameterizedTest
    @MethodSource("provideScoreAndExpectedMatchResult")
    @DisplayName("Score(점수)에 따라 승패를 결정한다.")
    public void decideByScoreTest(Score targetScore, MatchResult expectedResult) {
        // given
        Score otherScore = Score.from(9);

        // when
        MatchResult actualResult = MatchResult.decideByScore(targetScore, otherScore);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideScoreAndExpectedMatchResult() {
        return Stream.of(
                Arguments.of(Score.from(10), MatchResult.WIN),
                Arguments.of(Score.from(8), MatchResult.LOSE),
                Arguments.of(Score.from(9), MatchResult.PUSH)
        );
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedResult")
    @DisplayName("딜러와 플레이어의 카드 합을 비교하여 승패를 가린다")
    void determineOutcomeTest(List<Card> playerCards, List<Card> dealerCards, MatchResult expectedResult) {
        // given
        Player player = new Player("mia", new SequentialCardGenerator(playerCards));
        Dealer dealer = new Dealer(new SequentialCardGenerator(dealerCards));

        // when
        MatchResult actualResult = MatchResult.of(player, dealer);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.NINE)),
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.LOSE
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.NINE)),
                        MatchResult.WIN
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.PUSH
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBustCardsAndExpectedResult")
    @DisplayName("버스트인 쪽이 패배한다. 플레이어와 딜러가 둘 다 버스트면 플레이어가 패배한다")
    public void ensurePlayerWinWhenBothBustTest(List<Card> playerCards, List<Card> dealerCards, MatchResult expectedResult) {
        // given
        Player player = createPlayer(playerCards);
        Dealer dealer = createDealer(dealerCards);

        // when
        MatchResult actualResult = MatchResult.of(player, dealer);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideBustCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.TWO), new Card(Suit.HEART, Denomination.SIX), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.LOSE
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TWO), new Card(Suit.HEART, Denomination.TWO), new Card(Suit.HEART, Denomination.TWO)),
                        List.of(new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.WIN
                ),
                Arguments.of(
                        List.of(new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN), new Card(Suit.HEART, Denomination.TEN)),
                        List.of(new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.EIGHT), new Card(Suit.HEART, Denomination.TEN)),
                        MatchResult.LOSE
                )
        );
    }

    private Player createPlayer(final List<Card> playerCards) {
        CardGenerator cardGenerator = new SequentialCardGenerator(playerCards);
        Player player = new Player("mia", cardGenerator);
        player.hit(cardGenerator);
        return player;
    }

    private Dealer createDealer(final List<Card> dealerCards) {
        CardGenerator cardGenerator = new SequentialCardGenerator(dealerCards);
        Dealer dealer = new Dealer(cardGenerator);
        dealer.hitUntilEnd(cardGenerator);
        return dealer;
    }

    @Test
    @DisplayName("플레이어가 승리하면 베팅 금액만큼 금액을 더 받는다")
    void determineWinnerFinalBettingMoneyTest() {
        // given
        BettingMoney bettingMoney = BettingMoney.from(1000);

        // when
        BettingMoney finalBettingMoney = MatchResult.WIN.calculateFinalMoney(bettingMoney);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(2000);
    }

    @Test
    @DisplayName("플레이어가 지면 베팅 금액을 잃는다")
    void determineLoserFinalBettingMoneyTest() {
        // given
        BettingMoney bettingMoney = BettingMoney.from(1000);

        // when
        BettingMoney finalBettingMoney = MatchResult.LOSE.calculateFinalMoney(bettingMoney);

        // then
        assertThat(finalBettingMoney.getAmount()).isEqualTo(0);
    }
}
