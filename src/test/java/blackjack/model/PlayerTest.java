package blackjack.model;

import static blackjack.model.Rank.*;
import static blackjack.model.Suit.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card FOUR = new Card(Rank.FOUR, HEART);
    private static final Card FIVE = new Card(Rank.FIVE, CLOVER);
    private static final Card SIX = new Card(Rank.SIX, SPADE);
    private static final Card SEVEN = new Card(Rank.SEVEN, CLOVER);
    private static final Card EIGHT = new Card(Rank.EIGHT, DIAMOND);
    private static final Card NINE = new Card(Rank.NINE, SPADE);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Card KING = new Card(Rank.KING, CLOVER);
    private static final String GAMER_NAME = "pobi";

    @ParameterizedTest
    @MethodSource("provideDealerWinningCaseCards")
    @DisplayName("딜러가 이기는 경우 판별 테스트")
    void dealerIsWinner(Dealer dealer, Gamer gamer) {
        assertThat(dealer.match(gamer)).isEqualTo(Result.WIN);
    }

    private static Stream<Arguments> provideDealerWinningCaseCards() {
        return Stream.of(
            Arguments.of(new Dealer(EIGHT, JACK), new Gamer(GAMER_NAME, SEVEN, EIGHT)),
            Arguments.of(new Dealer(EIGHT, JACK), new Gamer(GAMER_NAME, JACK, QUEEN, FOUR)),
            Arguments.of(new Dealer(ACE, FIVE), new Gamer(GAMER_NAME, SEVEN, SIX)),
            Arguments.of(new Dealer(ACE, JACK, QUEEN), new Gamer(GAMER_NAME, QUEEN, JACK))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDealerLosingCaseCards")
    @DisplayName("딜러가 지는 경우 판별 테스트")
    void dealerIsLoser(Dealer dealer, Gamer gamer) {
        assertThat(dealer.match(gamer)).isEqualTo(Result.LOSS);
    }

    private static Stream<Arguments> provideDealerLosingCaseCards() {
        return Stream.of(
            Arguments.of(new Dealer(SEVEN, EIGHT), new Gamer(GAMER_NAME, QUEEN, JACK)),
            Arguments.of(new Dealer(JACK, QUEEN, FOUR), new Gamer(GAMER_NAME, QUEEN, JACK))
        );
    }

    @Test
    @DisplayName("비기는 경우 판별 테스트")
    void dealerIsDraw() {
        Dealer dealer = new Dealer(JACK, FOUR);
        assertThat(dealer.match(new Gamer(GAMER_NAME, EIGHT, SIX))).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("둘 다 버스트인 경우 테스트")
    void bothBust() {
        Dealer dealer = new Dealer(JACK, KING, SEVEN);
        assertThat(dealer.match(new Gamer(GAMER_NAME, EIGHT, SIX, KING))).isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("provideCardsForDealer")
    @DisplayName("딜러 카드 발급 가능 여부 확인 테스트")
    void dealerPossibleTakeCard(Dealer dealer, boolean expect) {
        assertThat(dealer.isHittable()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCardsForDealer() {
        return Stream.of(
            Arguments.of(new Dealer(JACK, SIX), true),
            Arguments.of(new Dealer(JACK, SEVEN), false),
            Arguments.of(new Dealer(ACE, SIX), false),
            Arguments.of(new Dealer(ACE, ACE), false)
        );
    }

    @Test
    @DisplayName("딜러 카드 발급")
    void takeCards() {
        Player dealer = new Dealer(JACK, THREE);
        dealer.take(ACE);
        assertThat(dealer.score()).isEqualTo(new Score(14));
    }

    @ParameterizedTest
    @MethodSource("providePlayers")
    @DisplayName("플레이어 점수 반환 테스트")
    void gamerScore(Player player, int expect) {
        assertThat(player.score().getValue()).isEqualTo(expect);
    }

    protected static Stream<Arguments> providePlayers() {
        return Stream.of(
            Arguments.of(new Gamer(GAMER_NAME, ACE, JACK), 21),
            Arguments.of(new Dealer(ACE, JACK, KING), 21),
            Arguments.of(new Dealer(ACE, ACE, NINE), 21),
            Arguments.of(new Gamer(GAMER_NAME, QUEEN, JACK, KING), 30),
            Arguments.of(new Gamer(GAMER_NAME, THREE, TWO), 5)
        );
    }

    @Test
    @DisplayName("딜러 카드 발급 실패")
    void takeInvalidCard() {
        Dealer dealer = new Dealer(JACK, SEVEN);
        assertThatThrownBy(() -> dealer.take(FOUR))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("딜러 카드 공개")
    void dealerOpenCard() {
        Dealer dealer = new Dealer(JACK, QUEEN);
        assertThat(dealer.openCards()).hasSize(1);
        assertThat(dealer.openCards()).contains(JACK);
    }

    @Test
    @DisplayName("게이머 첫 2장 카드 공개")
    void gamerOpenCards() {
        Gamer gamer = new Gamer(GAMER_NAME, QUEEN, FIVE);
        assertThat(gamer.openCards()).hasSize(2);
        assertThat(gamer.openCards()).contains(QUEEN, FIVE);
    }

    @Test
    @DisplayName("게이머 20이하일 경우 카드 발급 가능")
    void gamerCardTake() {
        Gamer gamer = new Gamer(GAMER_NAME, QUEEN, KING);
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("게이머 21이상일 경우 카드 발급 불가능")
    void gamerCardCantTake() {
        Gamer gamer = new Gamer(GAMER_NAME, QUEEN, ACE);
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("게이머 카드 발급")
    void gamerTakeCards() {
        Gamer dealer = new Gamer(GAMER_NAME, JACK, QUEEN);
        dealer.take(ACE);
        assertThat(dealer.score()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("게이머 카드 발급 실패")
    void gamerTakeInvalidCard() {
        Gamer dealer = new Gamer(GAMER_NAME, JACK, ACE);
        assertThatThrownBy(() -> dealer.take(FOUR))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("게이머 버스트 확인")
    void isBust() {
        Gamer gamer = new Gamer(GAMER_NAME, TWO, QUEEN, KING);
        assertThat(gamer.isBust()).isTrue();
    }
}
