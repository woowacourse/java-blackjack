package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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
        Cards cards = provideCards(2);

        // when
        player.receiveCards(cards);

        // then
        assertThat(player).isEqualTo(new Player("엠제이", cards));
    }

    @DisplayName("딜러는 카드 2개 중 1개만 보여준다.")
    @Test
    void showDealerCards() {
        // given
        final Cards cards = provideCards(2);
        player.receiveCards(cards);
        final List<Card> expected = List.of(new Card(Shape.SPADE, Denomination.A),
                new Card(Shape.SPADE, Denomination.TWO));

        // when
        final List<Card> playerCards = player.showCards();

        // then
        assertThat(playerCards).isEqualTo(expected);
    }

    @DisplayName("플레이어가 가진 카드의 합이 21 미만이면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void canGetMoreCard(final Cards cards, final boolean expected) {
        // given
        final Player player = new Player("엠제이", cards);

        // when & then
        assertThat(player.canGetMoreCard()).isEqualTo(expected);
    }

    private static Stream<Arguments> canGetMoreCard() {
        return Stream.of(
                Arguments.of(new Cards(List.of(
                        new Card(Shape.SPADE, Denomination.TEN),
                        new Card(Shape.SPADE, Denomination.NINE)
                )), true),
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
        player.receiveCards(cards);

        // when & then
        assertThat(player.calculateMaxSum()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMaxSum() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }
}
