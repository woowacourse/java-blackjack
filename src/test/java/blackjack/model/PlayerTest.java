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

    @ParameterizedTest
    @MethodSource("provideDealerWinningCaseCards")
    @DisplayName("딜러가 이기는 경우 판별 테스트")
    void dealerIsWinner(Dealer dealer, Cards playerCards) {
        assertThat(dealer.match(playerCards)).isEqualTo(Result.WIN);
    }

    private static Stream<Arguments> provideDealerWinningCaseCards() {
        return Stream.of(
            Arguments.of(new Dealer(new Card(EIGHT, DIAMOND), new Card(JACK, HEART)),
                new Cards(new Card(SEVEN, CLOVER), new Card(EIGHT, HEART))),
            Arguments.of(new Dealer(new Card(EIGHT, DIAMOND), new Card(JACK, HEART)),
                new Cards(new Card(JACK, HEART), new Card(QUEEN, HEART),
                    new Card(FOUR, DIAMOND))),
            Arguments.of(new Dealer(new Card(ACE, DIAMOND), new Card(FIVE, HEART)),
                new Cards(new Card(SEVEN, CLOVER), new Card(SIX, HEART))),
            Arguments.of(new Dealer(new Card(ACE, DIAMOND), new Card(JACK, HEART), new Card(QUEEN, CLOVER)),
                new Cards(new Card(QUEEN, CLOVER), new Card(JACK, HEART)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDealerLosingCaseCards")
    @DisplayName("딜러가 지는 경우 판별 테스트")
    void dealerIsLoser(Dealer dealer, Cards playerCards) {
        assertThat(dealer.match(playerCards)).isEqualTo(Result.LOSS);
    }

    private static Stream<Arguments> provideDealerLosingCaseCards() {
        return Stream.of(
            Arguments.of(new Dealer(new Card(SEVEN, CLOVER), new Card(EIGHT, HEART)),
                new Cards(new Card(QUEEN, CLOVER), new Card(JACK, HEART))),
            Arguments.of(new Dealer(new Card(JACK, HEART), new Card(QUEEN, HEART), new Card(FOUR, DIAMOND)),
                new Cards(new Card(QUEEN, CLOVER), new Card(JACK, HEART)))
        );
    }

    @Test
    @DisplayName("비기는 경우 판별 테스트")
    void dealerIsDraw() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(FOUR, HEART));
        assertThat(dealer.match(new Cards(new Card(EIGHT, CLOVER), new Card(SIX, HEART))))
            .isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("둘 다 버스트인 경우 테스트")
    void bothBust() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(KING, HEART), new Card(SEVEN, CLOVER));
        assertThat(dealer.match(new Cards(new Card(EIGHT, CLOVER), new Card(SIX, HEART), new Card(KING, HEART))))
            .isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("provideCardsForDealer")
    @DisplayName("딜러 카드 발급 가능 여부 확인 테스트")
    void dealerPossibleTakeCard(Dealer dealer, boolean expect) {
        assertThat(dealer.isHittable()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCardsForDealer() {
        return Stream.of(
            Arguments.of(new Dealer(new Card(JACK, DIAMOND), new Card(SIX, CLOVER)), true),
            Arguments.of(new Dealer(new Card(JACK, DIAMOND), new Card(SEVEN, CLOVER)), false),
            Arguments.of(new Dealer(new Card(ACE, DIAMOND), new Card(SIX, CLOVER)), false),
            Arguments.of(new Dealer(new Card(ACE, DIAMOND), new Card(ACE, CLOVER)), false)
        );
    }

    @Test
    @DisplayName("딜러 카드 발급")
    void takeCards() {
        Player dealer = new Dealer(new Card(JACK, DIAMOND), new Card(THREE, CLOVER));
        dealer.take(new Card(ACE, HEART));
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
            Arguments.of(new Gamer("player", new Card(ACE, SPADE), new Card(JACK, HEART)), 21),
            Arguments.of(new Dealer(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                new Card(KING, CLOVER)), 21),
            Arguments.of(new Dealer(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                new Card(NINE, CLOVER)), 21),
            Arguments.of(new Gamer("player", new Card(QUEEN, CLOVER), new Card(JACK, HEART),
                new Card(KING, DIAMOND)), 30),
            Arguments.of(new Gamer("player", new Card(THREE, DIAMOND), new Card(TWO, DIAMOND)), 5)
        );
    }

    @Test
    @DisplayName("딜러 카드 발급 실패")
    void takeInvalidCard() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(SEVEN, HEART));
        assertThatThrownBy(() -> dealer.take(new Card(FOUR, HEART)))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("딜러 카드 공개")
    void dealerOpenCard() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(QUEEN, HEART));
        assertThat(dealer.openCards()).hasSize(1);
        assertThat(dealer.openCards()).contains(new Card(JACK, DIAMOND));
    }

    @Test
    @DisplayName("게이머 첫 2장 카드 공개")
    void gamerOpenCards() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
        assertThat(gamer.openCards()).hasSize(2);
        assertThat(gamer.openCards()).contains(new Card(QUEEN, CLOVER), new Card(FIVE, HEART));
    }

    @Test
    @DisplayName("게이머 20이하일 경우 카드 발급 가능")
    void gamerCardTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(KING, SPADE));
        assertThat(gamer.isHittable()).isTrue();
    }

    @Test
    @DisplayName("게이머 21이상일 경우 카드 발급 불가능")
    void gamerCardCantTake() {
        Gamer gamer = new Gamer("pobi", new Card(QUEEN, CLOVER), new Card(ACE, SPADE));
        assertThat(gamer.isHittable()).isFalse();
    }

    @Test
    @DisplayName("게이머 카드 발급")
    void gamerTakeCards() {
        Gamer dealer = new Gamer("gamer", new Card(JACK, DIAMOND), new Card(QUEEN, CLOVER));
        dealer.take(new Card(ACE, HEART));
        assertThat(dealer.score()).isEqualTo(new Score(21));
    }

    @Test
    @DisplayName("게이머 카드 발급 실패")
    void gamerTakeInvalidCard() {
        Gamer dealer = new Gamer("gamer", new Card(JACK, DIAMOND), new Card(ACE, HEART));
        assertThatThrownBy(() -> dealer.take(new Card(FOUR, HEART)))
            .isInstanceOf(IllegalStateException.class);
    }
}
