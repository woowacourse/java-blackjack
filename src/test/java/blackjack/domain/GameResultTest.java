package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.Suit.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GameResultTest {
    @ParameterizedTest
    @MethodSource("calculateDealerRevenueDummy")
    @DisplayName("딜러의 수익을 계산한다.")
    void getDealerRevenue(List<Player> players, int expectedRevenue) {
        // given
        Dealer dealer = new Dealer(
                ParticipantCardsFixture.createParticipantsCards(  // 17점
                        List.of(Card.of(CLOVER, NINE), Card.of(HEART, EIGHT)), List.of())
        );

        // when
        GameResult gameResult = new GameResult(dealer, players);

        // expected
        assertThat(gameResult.getDealerRevenue()).isEqualTo(expectedRevenue);
    }

    static Stream<Arguments> calculateDealerRevenueDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, THREE),   // 17점 (플레이어 무승부)
                                                Card.of(DIAMOND, SEVEN)),
                                        List.of(Card.of(SPADE, TWO),
                                                Card.of(HEART, FIVE))), "vero", 1000),
                                new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, SIX),   // 11점 (플레이어 패배)
                                                Card.of(DIAMOND, FIVE)),
                                        List.of()), "poi", 2000)), 2000
                ), Arguments.arguments(
                        List.of(new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, THREE),   // 20점 (플레이어 승리)
                                                Card.of(DIAMOND, SEVEN)),
                                        List.of(Card.of(SPADE, FIVE),
                                                Card.of(HEART, FIVE))), "vero", 1000),
                                new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, ACE),   // 21점 블랙잭 (플레이어 블랙잭 승리)
                                                Card.of(DIAMOND, KING)),
                                        List.of()), "poi", 2000)), -3000
                ), Arguments.arguments(
                        List.of(new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, THREE),   // 21점 블랙잭 (플레이어 무승부)
                                                Card.of(DIAMOND, SEVEN)),
                                        List.of(Card.of(SPADE, FIVE),
                                                Card.of(HEART, FIVE))), "vero", 1000),
                                new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, ACE),   // 19점 (플레이어 패배)
                                                Card.of(DIAMOND, KING)),
                                        List.of()), "poi", 2000)), -3000
                )
        );
    }

    @ParameterizedTest
    @MethodSource("calculateBlackJackRevenueDummy")
    @DisplayName("딜러가 블랙잭일 때 수익을 계산한다.")
    void getDealerBlackJackRevenue(List<Player> players, int expectedRevenue) {
        // given
        Dealer dealer = new Dealer(
                ParticipantCardsFixture.createParticipantsCards(  // 블랙잭
                        List.of(Card.of(CLOVER, QUEEN), Card.of(HEART, ACE)), List.of())
        );

        // when
        GameResult gameResult = new GameResult(dealer, players);

        // expected
        assertThat(gameResult.getDealerRevenue()).isEqualTo(expectedRevenue);
    }

    static Stream<Arguments> calculateBlackJackRevenueDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of(new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, KING),   // 21점 블랙잭 (플레이어 블랙잭 무승부)
                                                Card.of(DIAMOND, ACE)),
                                        List.of()), "vero", 1000),
                                new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, SIX),   // 11점 (플레이어 패배)
                                                Card.of(DIAMOND, FIVE)),
                                        List.of()), "poi", 2000)), 2000
                ), Arguments.arguments(
                        List.of(new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(SPADE, FIVE),   // 21점 (플레이어 무승부)
                                                Card.of(HEART, ACE)),
                                        List.of(Card.of(CLOVER, FIVE))), "vero", 1000),
                                new Player(ParticipantCardsFixture.createParticipantsCards(
                                        List.of(Card.of(DIAMOND, ACE),   // 17점 (플레이어 패배)
                                                Card.of(DIAMOND, SEVEN)),
                                        List.of()), "poi", 2000)), 3000
                )
        );
    }

}
