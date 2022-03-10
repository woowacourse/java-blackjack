package blackjack;

import static blackjack.Rank.*;
import static blackjack.Suit.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.cards.Cards;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

    @ParameterizedTest
    @MethodSource("provideDealerWinningCaseCards")
    @DisplayName("딜러가 이기는 경우 판별 테스트")
    void dealerIsWinner(List<Card> cards, Cards playerCards) {
        Dealer dealer = new Dealer(cards.toArray(Card[]::new));
        assertThat(dealer.match(playerCards)).isEqualTo(Result.WIN);
    }

    private static Stream<Arguments> provideDealerWinningCaseCards() {
        return Stream.of(
            Arguments.of(List.of(new Card(EIGHT, DIAMOND), new Card(JACK, HEART)),
                Cards.mixHandCards(new Card(SEVEN, CLOVER), new Card(EIGHT, HEART))),
            Arguments.of(List.of(new Card(EIGHT, DIAMOND), new Card(JACK, HEART)),
                Cards.mixHandCards(new Card(JACK, HEART), new Card(QUEEN, HEART),
                    new Card(FOUR, DIAMOND))),
            Arguments.of(List.of(new Card(ACE, DIAMOND), new Card(FIVE, HEART)),
                Cards.mixHandCards(new Card(SEVEN, CLOVER), new Card(SIX, HEART))),
            Arguments.of(List.of(new Card(ACE, DIAMOND), new Card(JACK, HEART), new Card(QUEEN, CLOVER)),
                Cards.mixHandCards(new Card(QUEEN, CLOVER), new Card(JACK, HEART)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDealerLosingCaseCards")
    @DisplayName("딜러가 지는 경우 판별 테스트")
    void dealerIsLoser(List<Card> dealerCards, Cards playerCards) {
        Dealer dealer = new Dealer(dealerCards.toArray(Card[]::new));
        assertThat(dealer.match(playerCards)).isEqualTo(Result.LOSS);
    }

    private static Stream<Arguments> provideDealerLosingCaseCards() {
        return Stream.of(
            Arguments.of(List.of(new Card(SEVEN, CLOVER), new Card(EIGHT, HEART)),
                Cards.mixHandCards(new Card(QUEEN, CLOVER), new Card(JACK, HEART))),
            Arguments.of(List.of(new Card(JACK, HEART), new Card(QUEEN, HEART), new Card(FOUR, DIAMOND)),
                Cards.mixHandCards(new Card(QUEEN, CLOVER), new Card(JACK, HEART)))
        );
    }

    @Test
    @DisplayName("비기는 경우 판별 테스트")
    void dealerIsDraw() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(FOUR, HEART));
        assertThat(dealer.match(Cards.mixHandCards(new Card(EIGHT, CLOVER), new Card(SIX, HEART))))
            .isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("둘 다 버스트인 경우 테스트")
    void bothBust() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(KING, HEART), new Card(SEVEN, CLOVER));
        assertThat(dealer.match(Cards.mixHandCards(new Card(EIGHT, CLOVER), new Card(SIX, HEART), new Card(KING, HEART))))
            .isEqualTo(Result.WIN);
    }

    @ParameterizedTest
    @MethodSource("provideCardsForDealer")
    @DisplayName("카드 발급 가능 여부 확인 테스트")
    void possibleTakeCard(List<Card> cards, boolean expect) {
        Dealer dealer = new Dealer(cards.toArray(Card[]::new));
        assertThat(dealer.isPossibleTakeCard()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideCardsForDealer() {
        return Stream.of(
            Arguments.of(List.of(new Card(JACK, DIAMOND), new Card(SIX, CLOVER)), true),
            Arguments.of(List.of(new Card(JACK, DIAMOND), new Card(SEVEN, CLOVER)), false),
            Arguments.of(List.of(new Card(ACE, DIAMOND), new Card(SIX, CLOVER)), false),
            Arguments.of(List.of(new Card(ACE, DIAMOND), new Card(ACE, CLOVER)), false)
        );
    }

    @Test
    @Disabled
    @DisplayName("딜러 카드 발급")
    void takeCards() {
        Dealer dealer = new Dealer(new Card(JACK, DIAMOND), new Card(THREE, CLOVER));
        dealer.take(new Card(ACE, HEART));
        assertThat(dealer.score()).isEqualTo(new Score(14));
    }
}
