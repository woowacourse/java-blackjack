package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideBiggerAceCards;
import static blackjack.fixture.TestFixture.provideBiggerAndSmallerAceCards;
import static blackjack.fixture.TestFixture.provideCards;
import static blackjack.fixture.TestFixture.provideEmptyCards;
import static blackjack.fixture.TestFixture.provideSmallerAceCards;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardScore;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.participant.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(provideEmptyCards(), "엠제이", 10_000);
    }

    @DisplayName("이름과 베팅 금액으로 Player 객체를 생성한다.")
    @Test
    void createAttendee() {
        // given
        String nickname = "pobi";

        // when & then
        assertThatCode(() -> new Player(provideEmptyCards(), nickname, 10_000))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10000})
    @DisplayName("베팅 금액이 음수일 경우 예외가 발생한다")
    void createWithBettingAmountBelowZero(final int bettingAmount) {
        // Given

        // When & Then
        Assertions.assertThatThrownBy(() -> new Player(provideEmptyCards(), "밍트", bettingAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 베팅 금액을 양수로 입력해주세요.");
    }

    @DisplayName("카드들을 받는다.")
    @Test
    void receiveCards() {
        // given
        Hand hand = provideCards(2);

        // when
        player.receiveCards(hand);

        // then
        assertThat(player).isEqualTo(new Player(hand, "엠제이", 10_000));
    }

    @DisplayName("플레이어는 모든 카드를 보여준다.")
    @Test
    void showPlayerAllCards() {
        // given
        final Hand hand = provideCards(2);
        player.receiveCards(hand);

        // when
        final Hand playerHand = player.showAllCards();

        // then
        assertThat(playerHand.getHand()).hasSize(2);
    }

    @DisplayName("플레이어는 2장을 보여준다.")
    @Test
    void showPlayerInitialCards() {
        // given
        final Hand hand = provideCards(2);
        player.receiveCards(hand);

        // when
        final Hand playerHand = player.showInitialCards();

        // then
        assertThat(playerHand.getHand()).hasSize(2);
    }


    @DisplayName("플레이어가 가진 카드의 합이 21 미만이면 true를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void canHit(final Hand hand, final boolean expected) {
        // given
        final Player player = new Player(hand, "엠제이", 10_000);

        // when & then
        assertThat(player.canHit()).isEqualTo(expected);
    }

    private static Stream<Arguments> canHit() {
        return Stream.of(
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, CardScore.TEN),
                        new Card(Shape.SPADE, CardScore.NINE)
                )), true),
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, CardScore.A),
                        new Card(Shape.SPADE, CardScore.K)
                )), true),
                Arguments.of(new Hand(List.of(
                        new Card(Shape.SPADE, CardScore.K),
                        new Card(Shape.SPADE, CardScore.Q),
                        new Card(Shape.SPADE, CardScore.A)
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
        assertThat(player.calculateScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> calculateMaxScore() {
        return Stream.of(
                Arguments.of(provideSmallerAceCards(), 18),
                Arguments.of(provideBiggerAceCards(), 21),
                Arguments.of(provideBiggerAndSmallerAceCards(), 17)
        );
    }
}
