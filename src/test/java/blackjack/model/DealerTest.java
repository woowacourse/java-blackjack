package blackjack.model;

import static blackjack.model.Rank.*;
import static blackjack.model.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

    @ParameterizedTest(name = "[{index}] 딜러가 이기는 경우")
    @MethodSource("provideDealerWinningCaseCards")
    @DisplayName("딜러가 이기는 경우 판별 테스트")
    void dealerIsWinner(Dealer dealer, Cards playerCards) {
        assertThat(dealer.match(playerCards)).isEqualTo(Result.WIN);
    }

    private static Stream<Arguments> provideDealerWinningCaseCards() {
        return Stream.of(
                Arguments.of(
                        new Dealer(List.of(new Card(EIGHT, DIAMOND), new Card(JACK, HEART))),
                        new Cards(List.of(new Card(SEVEN, CLOVER), new Card(EIGHT, HEART)))),
                Arguments.of(
                        new Dealer(List.of(new Card(EIGHT, DIAMOND), new Card(JACK, HEART))),
                        new Cards(List.of(new Card(JACK, HEART), new Card(QUEEN, HEART), new Card(FOUR, DIAMOND)))),
                Arguments.of(
                        new Dealer(List.of(new Card(ACE, DIAMOND), new Card(FIVE, HEART))),
                        new Cards(List.of(new Card(SEVEN, CLOVER), new Card(SIX, HEART)))),
                Arguments.of(
                        new Dealer(List.of(new Card(ACE, DIAMOND), new Card(JACK, HEART), new Card(QUEEN, CLOVER))),
                        new Cards(List.of(new Card(QUEEN, CLOVER), new Card(JACK, HEART))))
        );
    }

    @ParameterizedTest(name = "[{index}] 딜러가 지는 경우")
    @MethodSource("provideDealerLosingCaseCards")
    @DisplayName("딜러가 지는 경우 판별 테스트")
    void dealerIsLoser(Dealer dealer, Cards playerCards) {
        assertThat(dealer.match(playerCards)).isEqualTo(Result.LOSE);
    }

    private static Stream<Arguments> provideDealerLosingCaseCards() {
        return Stream.of(
                Arguments.of(
                        new Dealer(List.of(new Card(SEVEN, CLOVER), new Card(EIGHT, HEART))),
                        new Cards(List.of(new Card(QUEEN, CLOVER), new Card(JACK, HEART)))),
                Arguments.of(
                        new Dealer(List.of(new Card(JACK, HEART), new Card(QUEEN, HEART), new Card(FOUR, DIAMOND))),
                        new Cards(List.of(new Card(QUEEN, CLOVER), new Card(JACK, HEART))))
        );
    }

    @ParameterizedTest(name = "[{index}] 카드 발급 가능 여부")
    @MethodSource("provideCardsForDealer")
    @DisplayName("딜러 카드 발급 가능 여부 확인 테스트")
    void dealerPossibleTakeCard(Dealer dealer, boolean expect) {
        assertThat(dealer.isHittable()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCardsForDealer() {
        return Stream.of(
                Arguments.of(new Dealer(List.of(new Card(JACK, DIAMOND), new Card(SIX, CLOVER))), true),
                Arguments.of(new Dealer(List.of(new Card(JACK, DIAMOND), new Card(SEVEN, CLOVER))), false),
                Arguments.of(new Dealer(List.of(new Card(ACE, DIAMOND), new Card(SIX, CLOVER))), false),
                Arguments.of(new Dealer(List.of(new Card(ACE, DIAMOND), new Card(ACE, CLOVER))), false)
        );
    }

    @Test
    @DisplayName("딜러 판정 결과 비기는 경우 판별 테스트")
    void dealerIsDraw() {
        Dealer dealer = new Dealer(List.of(new Card(JACK, DIAMOND), new Card(FOUR, HEART)));
        assertThat(dealer.match(new Cards(List.of(new Card(EIGHT, CLOVER), new Card(SIX, HEART)))))
                .isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("딜러 판정 결과 둘 다 버스트인 경우 테스트")
    void bothBust() {
        Dealer dealer = new Dealer(
                List.of(new Card(JACK, DIAMOND), new Card(KING, HEART), new Card(SEVEN, CLOVER)));
        assertThat(dealer.match(new Cards(
                List.of(new Card(EIGHT, CLOVER), new Card(SIX, HEART), new Card(KING, HEART)))))
                .isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("딜러 카드 발급")
    void takeCards() {
        Player dealer = new Dealer(List.of(new Card(JACK, DIAMOND), new Card(THREE, CLOVER)));
        dealer.take(new Card(ACE, HEART));
        assertThat(dealer.score()).isEqualTo(new Score(14));
    }

    @Test
    @DisplayName("딜러 카드 발급 실패")
    void takeInvalidCard() {
        Dealer dealer = new Dealer(List.of(new Card(JACK, DIAMOND), new Card(SEVEN, HEART)));
        assertThatThrownBy(() -> dealer.take(new Card(FOUR, HEART)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("딜러 카드 공개")
    void dealerOpenCard() {
        Dealer dealer = new Dealer(List.of(new Card(JACK, DIAMOND), new Card(QUEEN, HEART)));
        assertThat(dealer.openCards()).hasSize(1);
        assertThat(dealer.openCards()).contains(new Card(JACK, DIAMOND));
    }
}
