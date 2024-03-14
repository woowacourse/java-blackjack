package domain.blackjack;

import static domain.blackjack.TestHoldingCards.BLACK_JACK;
import static domain.blackjack.TestHoldingCards.DEAD_CARDS;
import static domain.blackjack.TestHoldingCards.ONLY_SEVEN_HEART;
import static domain.blackjack.TestHoldingCards.ONLY_SIX_HEART;
import static domain.blackjack.TestHoldingCards.TWO_SIX_CARDS;
import static domain.blackjack.TestHoldingCards.WIN_CARDS_WITHOUT_ACE;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EarnMoneyCalculatorTest {

    public static Stream<Arguments> calculateBettingMoneyWhenDealerBustParameters() {
        return Stream.of(
                Arguments.of(BLACK_JACK, 10000, 15000),
                Arguments.of(ONLY_SIX_HEART, 10000, 10000),
                Arguments.of(DEAD_CARDS, 10000, 10000),
                Arguments.of(WIN_CARDS_WITHOUT_ACE, 10000, 10000)
        );
    }

    public static Stream<Arguments> calculateBettingMoneyWhenDealerNotBustParameters() {
        return Stream.of(
                Arguments.of(BLACK_JACK, 10000, 15000),
                Arguments.of(ONLY_SIX_HEART, 10000, -10000),
                Arguments.of(DEAD_CARDS, 10000, -10000),
                Arguments.of(WIN_CARDS_WITHOUT_ACE, 10000, 10000),
                Arguments.of(TWO_SIX_CARDS, 10000, 10000),
                Arguments.of(ONLY_SEVEN_HEART, 10000, 0)
        );
    }

    public static Stream<Arguments> calculateBettingMoneyWhenDealerIsBLackJackParameters() {
        return Stream.of(
                Arguments.of(BLACK_JACK, 10000, 0),
                Arguments.of(ONLY_SIX_HEART, 10000, -10000),
                Arguments.of(DEAD_CARDS, 10000, -10000),
                Arguments.of(WIN_CARDS_WITHOUT_ACE, 10000, -10000),
                Arguments.of(TWO_SIX_CARDS, 10000, -10000),
                Arguments.of(ONLY_SEVEN_HEART, 10000, -10000)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateBettingMoneyWhenDealerBustParameters")
    @DisplayName("딜러가 버스트일 떼 배팅 상금이 제대로 계산되는지 검증")
    void calculateBettingMoneyWhenDealerBust(HoldingCards playerHoldingCards, int bettingMoney, int expected) {
        Player player = Player.from("플레이어", playerHoldingCards, bettingMoney);
        Dealer dealer = Dealer.of(TestHoldingCards.DEAD_CARDS);
        GameResult gameResult = GameResultCalculator.calculate(player, dealer);

        Assertions.assertThat(EarnMoneyCalculator.calculateEarnMoney(bettingMoney, gameResult))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("calculateBettingMoneyWhenDealerNotBustParameters")
    @DisplayName("딜러가 버스트가 아닐 때 배팅 상금이 제대로 계산되는지 검증")
    void calculateBettingMoneyWhenDealerNotBust(HoldingCards playerHoldingCards, int bettingMoney, int expected) {
        Player player = Player.from("플레이어", playerHoldingCards, bettingMoney);
        Dealer dealer = Dealer.of(ONLY_SEVEN_HEART);
        GameResult gameResult = GameResultCalculator.calculate(player, dealer);

        Assertions.assertThat(EarnMoneyCalculator.calculateEarnMoney(bettingMoney, gameResult))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("calculateBettingMoneyWhenDealerIsBLackJackParameters")
    @DisplayName("딜러가 블랙잭일 때 배팅 상금이 제대로 계산되는지 검증")
    void calculateBettingMoneyWhenDealerIsBLackJack(HoldingCards playerHoldingCards, int bettingMoney, int expected) {
        Player player = Player.from("플레이어", playerHoldingCards, bettingMoney);
        Dealer dealer = Dealer.of(BLACK_JACK);
        GameResult gameResult = GameResultCalculator.calculate(player, dealer);

        Assertions.assertThat(EarnMoneyCalculator.calculateEarnMoney(bettingMoney, gameResult))
                .isEqualTo(expected);
    }
}
