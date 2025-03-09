package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(provideEmptyCards());
    }

    @DisplayName("카드들을 받는다.")
    @Test
    void receiveCards() {
        // given
        final Cards cards = provideCards(2);

        // when
        dealer.receiveCards(cards);

        // then
        assertThat(dealer).isEqualTo(new Dealer(cards));
    }

    @DisplayName("딜러는 카드 2개 중 1개만 보여준다.")
    @Test
    void showDealerCards() {
        // given
        final Cards cards = provideCards(2);
        dealer.receiveCards(cards);

        // when
        final List<Card> dealerCards = dealer.showOneCard();

        // then
        assertThat(dealerCards).isEqualTo(List.of(new Card(Shape.SPADE, Denomination.A)));
    }

    @DisplayName("딜러가 가진 카드의 합이 16 이하면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void canGetMoreCard(final Cards cards, final boolean expected) {
        // given
        final Dealer dealer = new Dealer(cards);

        // when & then
        assertThat(dealer.canGetMoreCard()).isEqualTo(expected);
    }

    private static Stream<Arguments> canGetMoreCard() {
        return Stream.of(
                Arguments.of(new Cards(List.of(
                        new Card(Shape.SPADE, Denomination.TEN),
                        new Card(Shape.SPADE, Denomination.NINE)
                )), false),
                Arguments.of(new Cards(List.of(
                        new Card(Shape.SPADE, Denomination.A),
                        new Card(Shape.SPADE, Denomination.K)
                )), true),
                Arguments.of(new Cards(List.of(
                        new Card(Shape.SPADE, Denomination.K),
                        new Card(Shape.SPADE, Denomination.Q),
                        new Card(Shape.SPADE, Denomination.A)
                )), false)
        );
    }

    @DisplayName("카드 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateMaxSum(final Cards cards, final int expected) {
        // given
        dealer.receiveCards(cards);

        // when & then
        assertThat(dealer.calculateMaxSum()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMaxSum() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }
}