package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.player.Hand;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatchResultTest {

    private static final Hand HAND_BLACKJACK = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.ACE),
            new Card(Shape.DIAMOND, Rank.TEN)
    ));
    private static final Hand HAND_22 = new Hand(List.of(
            new Card(Shape.HEART, Rank.TEN),
            new Card(Shape.CLOVER, Rank.TEN),
            new Card(Shape.DIAMOND, Rank.TWO)
    ));
    private static final Hand HAND_21 = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.JACK),
            new Card(Shape.DIAMOND, Rank.TEN),
            new Card(Shape.HEART, Rank.ACE)
    ));
    private static final Hand HAND_20 = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.JACK),
            new Card(Shape.DIAMOND, Rank.TEN)
    ));

    @ParameterizedTest
    @MethodSource("playerWinCases")
    @DisplayName("플레이어가 배팅 금액만큼 돈을 버는 경우를 올바르게 판단한다.")
    void playerWinTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculatePrizeRate(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.PLAYER_WIN.getPrizeRate());
    }

    static Stream<Arguments> playerWinCases() {
        return Stream.of(
                Arguments.of(HAND_20, HAND_22),
                Arguments.of(HAND_21, HAND_20)
        );
    }

    @ParameterizedTest
    @MethodSource("playerBlackJackCases")
    @DisplayName("플레이어가 배팅 금액의 1.5배만큼 돈을 버는 경우를 올바르게 판단한다.")
    void playerBlackJackTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculatePrizeRate(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.PLAYER_BLACKJACK.getPrizeRate());
    }

    static Stream<Arguments> playerBlackJackCases() {
        return Stream.of(
                Arguments.of(HAND_BLACKJACK, HAND_22),
                Arguments.of(HAND_BLACKJACK, HAND_21),
                Arguments.of(HAND_BLACKJACK, HAND_20)
        );
    }

    @ParameterizedTest
    @MethodSource("dealerWinCases")
    @DisplayName("플레이어가 배팅 금액을 잃는 경우를 올바르게 판단한다.")
    void dealerWinTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculatePrizeRate(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.DEALER_WIN.getPrizeRate());
    }

    static Stream<Arguments> dealerWinCases() {
        return Stream.of(
                Arguments.of(HAND_22, HAND_20),
                Arguments.of(HAND_22, HAND_22),
                Arguments.of(HAND_20, HAND_21),
                Arguments.of(HAND_21, HAND_BLACKJACK)
        );
    }

    @ParameterizedTest
    @MethodSource("tieCases")
    @DisplayName("플레이어가 배팅 금액을 돌려받는 경우를 올바르게 판단한다.")
    void tieTest(Hand playerHand, Hand dealerHand) {
        double result = MatchResult.calculatePrizeRate(playerHand, dealerHand);

        assertThat(result).isEqualTo(MatchResult.TIE.getPrizeRate());
    }

    static Stream<Arguments> tieCases() {
        return Stream.of(
                Arguments.of(HAND_BLACKJACK, HAND_BLACKJACK),
                Arguments.of(HAND_21, HAND_21),
                Arguments.of(HAND_20, HAND_20)
        );
    }
}
