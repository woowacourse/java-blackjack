package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class DealerTest {

    private final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card secondCard = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(firstCard, secondCard);
    }

    @Test
    @DisplayName("딜러 초기화 테스트")
    void initTest() {
        User dealer = new Dealer(initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(dealer.getName()).isEqualTo(Dealer.DEALER_NAME_CODE);
            softly.assertThat(dealer.getHandholdingCards()).containsExactly(firstCard, secondCard);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        User dealer = new Dealer(initialGroup);

        assertThat(dealer.getInitialHoldingCards()).containsExactly(firstCard);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 뽑아야 하는지 확인 테스트")
    void isOverDrawTest() {
        final Dealer dealer = new Dealer(initialGroup);

        boolean isOverDraw = dealer.isOverDraw();

        assertThat(isOverDraw).isTrue();
    }

    @Nested
    @DisplayName("플레이어 점수로 블랙젝 결과 테스트")
    static class JudgePlayerScoreTest {

        private static final Card aceCard = new Card(CardShape.DIAMOND, CardNumber.ACE);
        private static final Card twoCard = new Card(CardShape.DIAMOND, CardNumber.TWO);
        private static final Card nineCard = new Card(CardShape.DIAMOND, CardNumber.NINE);
        private static final Card tenCard = new Card(CardShape.DIAMOND, CardNumber.TEN);

        private static final CardGroup thirteenCardGroup;
        private static final CardGroup nineteenCardGroup;
        private static final CardGroup twentyoneCardGroup;
        private static final CardGroup blackjackCardGroup;
        private static final CardGroup bustedCardGame;

        static {
            thirteenCardGroup = new CardGroup(aceCard, twoCard);

            nineteenCardGroup = new CardGroup(nineCard, tenCard);

            twentyoneCardGroup = new CardGroup(twoCard, nineCard);
            twentyoneCardGroup.add(tenCard);

            blackjackCardGroup = new CardGroup(aceCard, tenCard);

            bustedCardGame = new CardGroup(aceCard, twoCard);
            bustedCardGame.add(nineCard);
            bustedCardGame.add(tenCard);
        }

        @ParameterizedTest(name = "result: {1}")
        @DisplayName("19점 딜러와 비교")
        @MethodSource("provideFor19Dealer")
        void judgeWith19Dealer(final CardGroup playersHand, final GameResult expectedResult) {
            final CardGroup dealersHand = new CardGroup(new Card(CardShape.SPADE, CardNumber.JACK), new Card(CardShape.SPADE, CardNumber.NINE));
            final Dealer dealer = new Dealer(dealersHand);
            final Player player = new Player("test", playersHand);

            final GameResult actualResult = dealer.judgePlayerGameResult(player.getScore());

            assertThat(actualResult).isEqualTo(expectedResult);
        }

        private static Stream<Arguments> provideFor19Dealer() {
            return Stream.of(
                    Arguments.of(thirteenCardGroup, GameResult.LOSE),
                    Arguments.of(nineteenCardGroup, GameResult.TIE),
                    Arguments.of(twentyoneCardGroup, GameResult.NORMAL_WIN),
                    Arguments.of(blackjackCardGroup, GameResult.BLACKJACK_WIN),
                    Arguments.of(bustedCardGame, GameResult.LOSE)
            );
        }

        @ParameterizedTest(name = "result: {1}")
        @DisplayName("21점 딜러와 비교")
        @MethodSource("provideFor21Dealer")
        void judgeWith21Dealer(final CardGroup playersHand, final GameResult expectedResult) {
            final CardGroup dealersHand = new CardGroup(new Card(CardShape.SPADE, CardNumber.JACK), new Card(CardShape.SPADE, CardNumber.NINE));
            dealersHand.add(new Card(CardShape.SPADE, CardNumber.TWO));
            final Dealer dealer = new Dealer(dealersHand);
            final Player player = new Player("test", playersHand);

            final GameResult actualResult = dealer.judgePlayerGameResult(player.getScore());

            assertThat(actualResult).isEqualTo(expectedResult);
        }

        private static Stream<Arguments> provideFor21Dealer() {
            return Stream.of(
                    Arguments.of(thirteenCardGroup, GameResult.LOSE),
                    Arguments.of(twentyoneCardGroup, GameResult.TIE),
                    Arguments.of(blackjackCardGroup, GameResult.BLACKJACK_WIN),
                    Arguments.of(bustedCardGame, GameResult.LOSE)
            );
        }

        @ParameterizedTest(name = "result: {1}")
        @DisplayName("블랙잭 딜러와 비교")
        @MethodSource("provideForBlackjackDealer")
        void judgeWithBlackjackDealer(final CardGroup playersHand, final GameResult expectedResult) {
            final CardGroup dealersHand = new CardGroup(new Card(CardShape.SPADE, CardNumber.JACK), new Card(CardShape.SPADE, CardNumber.ACE));
            final Dealer dealer = new Dealer(dealersHand);
            final Player player = new Player("test", playersHand);

            final GameResult actualResult = dealer.judgePlayerGameResult(player.getScore());

            assertThat(actualResult).isEqualTo(expectedResult);
        }

        private static Stream<Arguments> provideForBlackjackDealer() {
            return Stream.of(
                    Arguments.of(thirteenCardGroup, GameResult.LOSE),
                    Arguments.of(twentyoneCardGroup, GameResult.LOSE),
                    Arguments.of(blackjackCardGroup, GameResult.TIE),
                    Arguments.of(bustedCardGame, GameResult.LOSE)
            );
        }

        @ParameterizedTest(name = "result: {1}")
        @DisplayName("버스트된 딜러와 비교")
        @MethodSource("provideForBustedDealer")
        void judgeWithBustedDealer(final CardGroup playersHand, final GameResult expectedResult) {
            final CardGroup dealersHand = new CardGroup(new Card(CardShape.SPADE, CardNumber.JACK), new Card(CardShape.SPADE, CardNumber.NINE));
            dealersHand.add(new Card(CardShape.SPADE, CardNumber.THREE));
            final Dealer dealer = new Dealer(dealersHand);
            final Player player = new Player("test", playersHand);

            final GameResult actualResult = dealer.judgePlayerGameResult(player.getScore());

            assertThat(actualResult).isEqualTo(expectedResult);
        }

        private static Stream<Arguments> provideForBustedDealer() {
            return Stream.of(
                    Arguments.of(thirteenCardGroup, GameResult.NORMAL_WIN),
                    Arguments.of(blackjackCardGroup, GameResult.BLACKJACK_WIN),
                    Arguments.of(bustedCardGame, GameResult.LOSE)
            );
        }

    }

}
