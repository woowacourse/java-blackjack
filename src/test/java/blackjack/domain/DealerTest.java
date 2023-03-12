package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import blackjack.domain.card.ParticipantCards;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @ParameterizedTest
    @MethodSource("isHittableDummy")
    @DisplayName("딜러가 카드를 뽑을 수 있는지 확인한다.")
    void isHittable(final List<Card> initialCards, final List<Card> additionalCards, final boolean expected) {
        Participant player = new Dealer(ParticipantCardsFixture.createParticipantsCards(initialCards, additionalCards));

        assertThat(player.isHittable()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("judgeResultDummy")
    @DisplayName("딜러가 플레이어의 결과를 판단한다.")
    void judgeResult(
            final List<Card> playerInitialCards,
            final List<Card> playerAdditionalCards,
            final List<Card> dealerInitialCards,
            final List<Card> dealerAdditionalCards,
            final ResultType expectedResult
    ) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(playerInitialCards, playerAdditionalCards);
        ParticipantCards dealerCards = ParticipantCardsFixture.createParticipantsCards(dealerInitialCards, dealerAdditionalCards);

        Player player = new Player(participantsCards, "베로", 1000);
        Dealer dealer = new Dealer(dealerCards);

        assertThat(dealer.judgePlayerResult(player)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> isHittableDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 히트 가능
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.THREE)),
                        List.of(
                                Card.of(Suit.SPADE, CardNumber.THREE),
                                Card.of(Suit.HEART, CardNumber.EIGHT)
                        ), true),
                Arguments.arguments(
                        // 히트 불가능
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.THREE)),
                        List.of(
                                Card.of(Suit.SPADE, CardNumber.ACE),
                                Card.of(Suit.CLOVER, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        // 히트 불가능
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.FOUR)),
                        List.of(
                                Card.of(Suit.SPADE, CardNumber.ACE),
                                Card.of(Suit.CLOVER, CardNumber.QUEEN),
                                Card.of(Suit.HEART, CardNumber.JACK),
                                Card.of(Suit.DIAMOND, CardNumber.THREE)
                        ), false)
        );
    }

    static Stream<Arguments> judgeResultDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 플레이어 딜러 모두 버스트하는 경우
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.NINE),
                        Card.of(Suit.DIAMOND, CardNumber.QUEEN)),
                        List.of(Card.of(Suit.SPADE, CardNumber.JACK),
                                Card.of(Suit.HEART, CardNumber.KING)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                        Card.of(Suit.HEART, CardNumber.QUEEN)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.JACK),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어만 버스트하는 경우
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.NINE),
                        Card.of(Suit.DIAMOND, CardNumber.QUEEN)),
                        List.of(Card.of(Suit.SPADE, CardNumber.JACK),
                                Card.of(Suit.HEART, CardNumber.KING)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                        Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.THREE),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어가 승리한다.
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.FIVE)),
                        List.of(Card.of(Suit.SPADE, CardNumber.THREE),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                        Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.TWO),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 플레이어 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어가 패배한다.
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.FIVE)),
                        List.of(Card.of(Suit.SPADE, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                        Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.THREE),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어 딜러 모두 무승부한다.
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                        Card.of(Suit.DIAMOND, CardNumber.FIVE)),
                        List.of(Card.of(Suit.SPADE, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                        Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.TWO),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 플레이어 무승부
                        ResultType.PUSH
                )
        );
    }

    @ParameterizedTest
    @MethodSource("judgeDealerResultDummy")
    @DisplayName("딜러는 플레이어의 결과의 반대 결과를 갖는다.")
    void getReverseResult(
            final List<Card> playerInitialCards,
            final List<Card> playerAdditionalCards,
            final List<Card> dealerInitialCards,
            final List<Card> dealerAdditionalCards,
            final ResultType expectedResult
    ) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(playerInitialCards, playerAdditionalCards);
        ParticipantCards dealerCards = ParticipantCardsFixture.createParticipantsCards(dealerInitialCards, dealerAdditionalCards);

        Player player = new Player(participantsCards, "베로", 1000);
        Dealer dealer = new Dealer(dealerCards);

        ResultType playerResult = dealer.judgePlayerResult(player);
        ResultType dealerType = ResultType.getReverseType(playerResult);

        assertThat(dealerType).isEqualTo(expectedResult);
    }

    static Stream<Arguments> judgeDealerResultDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 플레이어 딜러 모두 버스트하는 경우
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.NINE),
                                Card.of(Suit.DIAMOND, CardNumber.QUEEN)),
                        List.of(Card.of(Suit.SPADE, CardNumber.JACK),
                                Card.of(Suit.HEART, CardNumber.KING)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.JACK),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어만 버스트하는 경우
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.NINE),
                                Card.of(Suit.DIAMOND, CardNumber.QUEEN)),
                        List.of(Card.of(Suit.SPADE, CardNumber.JACK),
                                Card.of(Suit.HEART, CardNumber.KING)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.THREE),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 승리
                        ResultType.WIN
                ),Arguments.arguments(
                        // 딜러만 버스트하는 경우
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.THREE),
                                Card.of(Suit.DIAMOND, CardNumber.EIGHT)),
                        List.of(Card.of(Suit.SPADE, CardNumber.FIVE),
                                Card.of(Suit.HEART, CardNumber.THREE)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.QUEEN),
                                Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.NINE),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어가 승리한다.
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                                Card.of(Suit.DIAMOND, CardNumber.FIVE)),
                        List.of(Card.of(Suit.SPADE, CardNumber.THREE),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.TWO),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어가 패배한다.
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                                Card.of(Suit.DIAMOND, CardNumber.FIVE)),
                        List.of(Card.of(Suit.SPADE, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.THREE),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어 딜러 모두 무승부한다.
                        // 플레이어
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                                Card.of(Suit.DIAMOND, CardNumber.FIVE)),
                        List.of(Card.of(Suit.SPADE, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        List.of(Card.of(Suit.HEART, CardNumber.TWO),
                                Card.of(Suit.HEART, CardNumber.FIVE)),
                        List.of(Card.of(Suit.CLOVER, CardNumber.TWO),
                                Card.of(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 무승부
                        ResultType.PUSH
                )
        );
    }
}
