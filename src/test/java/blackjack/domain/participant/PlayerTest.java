package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
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

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("엠제이", provideEmptyCards());
    }

    @DisplayName("이름으로 Player 객체를 생성한다.")
    @Test
    void createAttendee() {
        // given
        String nickname = "pobi";

        // when & then
        assertThatCode(() -> new Player(nickname, provideEmptyCards()))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드들을 받는다.")
    @Test
    void receiveCards() {
        // given
        Hand hand = provideCards(2);

        // when
        player.receiveCards(hand);

        // then
        assertThat(player).isEqualTo(new Player("엠제이", hand));
    }

    @DisplayName("플레이어는 모든 카드를 보여준다.")
    @Test
    void showPlayerAllCards() {
        // given
        final Hand hand = provideCards(2);
        player.receiveCards(hand);
        final List<Card> expected = List.of(new Card(Shape.SPADE, Denomination.A),
                new Card(Shape.SPADE, Denomination.TWO));

        // when
        final Hand playerHand = player.showAllCards();

        // then
        assertThat(playerHand.getHand()).isEqualTo(expected);
    }

    @DisplayName("플레이어는 1장만 보여준다.")
    @Test
    void showPlayerInitialCards() {
        // given
        final Hand hand = provideCards(2);
        player.receiveCards(hand);
        final List<Card> expected = List.of(new Card(Shape.SPADE, Denomination.A),
                new Card(Shape.SPADE, Denomination.TWO));

        // when
        final Hand playerHand = player.showInitialCards();

        // then
        assertThat(playerHand.getHand()).isEqualTo(expected);
    }


    @DisplayName("플레이어가 가진 카드의 합이 21 미만이면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void canGetMoreCard(final Hand hand, final boolean expected) {
        // given
        final Player player = new Player("엠제이", hand);

        // when & then
        assertThat(player.canGetMoreCard()).isEqualTo(expected);
    }

    private static Stream<Arguments> canGetMoreCard() {
        return Stream.of(
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, Denomination.TEN),
                        new Card(Shape.SPADE, Denomination.NINE)
                )), true),
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, Denomination.A),
                        new Card(Shape.SPADE, Denomination.K)
                )), true),
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, Denomination.K),
                        new Card(Shape.SPADE, Denomination.Q),
                        new Card(Shape.SPADE, Denomination.A)
                )), false)
        );
    }

    @DisplayName("카드 합을 구한다")
    @ParameterizedTest
    @MethodSource
    void calculateMaxScore(final Hand hand, final int expected) {
        // given
        player.receiveCards(hand);

        // when & then
        assertThat(player.calculateMaxScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMaxScore() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }
}
