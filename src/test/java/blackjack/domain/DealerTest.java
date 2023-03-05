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
    void isHittable(final Card cardOne, final Card cardTwo, final List<Card> additionalCards, final boolean expected) {
        Participant player = new Dealer(ParticipantCardsFixture.createParticipantsCards(cardOne, cardTwo, additionalCards));

        assertThat(player.isHittable()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("judgeResultDummy")
    @DisplayName("딜러가 플레이어의 결과를 판단한다.")
    void judgeResult(
            final Card playerCard1,
            final Card playerCard2,
            final List<Card> playerAdditionalCards,
            final Card dealerCard1,
            final Card dealerCard2,
            final List<Card> dealerAdditionalCards,
            final ResultType expectedResult
    ) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(playerCard1, playerCard2, playerAdditionalCards);
        ParticipantCards dealerCards = ParticipantCardsFixture.createParticipantsCards(dealerCard1, dealerCard2, dealerAdditionalCards);

        Player player = new Player(participantsCards, "베로");
        Dealer dealer = new Dealer(dealerCards);

        assertThat(dealer.judgeResult(player)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> isHittableDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 히트 가능
                        new Card(Suit.DIAMOND, CardNumber.TWO),
                        new Card(Suit.DIAMOND, CardNumber.THREE),
                        List.of(
                                new Card(Suit.SPADE, CardNumber.THREE),
                                new Card(Suit.HEART, CardNumber.EIGHT)
                        ), true),
                Arguments.arguments(
                        // 히트 불가능
                        new Card(Suit.DIAMOND, CardNumber.TWO),
                        new Card(Suit.DIAMOND, CardNumber.THREE),
                        List.of(
                                new Card(Suit.SPADE, CardNumber.ACE),
                                new Card(Suit.CLOVER, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        // 히트 불가능
                        new Card(Suit.DIAMOND, CardNumber.TWO),
                        new Card(Suit.DIAMOND, CardNumber.FOUR),
                        List.of(
                                new Card(Suit.SPADE, CardNumber.ACE),
                                new Card(Suit.CLOVER, CardNumber.QUEEN),
                                new Card(Suit.HEART, CardNumber.JACK),
                                new Card(Suit.DIAMOND, CardNumber.THREE)
                        ), false)
        );
    }

    static Stream<Arguments> judgeResultDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 플레이어 딜러 모두 버스트하는 경우
                        // 플레이어
                        new Card(Suit.DIAMOND, CardNumber.NINE),
                        new Card(Suit.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(Suit.SPADE, CardNumber.JACK),
                                new Card(Suit.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(Suit.HEART, CardNumber.TWO),
                        new Card(Suit.HEART, CardNumber.QUEEN),
                        List.of(new Card(Suit.CLOVER, CardNumber.JACK),
                                new Card(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어만 버스트하는 경우
                        // 플레이어
                        new Card(Suit.DIAMOND, CardNumber.NINE),
                        new Card(Suit.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(Suit.SPADE, CardNumber.JACK),
                                new Card(Suit.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(Suit.HEART, CardNumber.TWO),
                        new Card(Suit.HEART, CardNumber.FIVE),
                        List.of(new Card(Suit.CLOVER, CardNumber.THREE),
                                new Card(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어가 승리한다.
                        // 플레이어
                        new Card(Suit.DIAMOND, CardNumber.TWO),
                        new Card(Suit.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(Suit.SPADE, CardNumber.THREE),
                                new Card(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(Suit.HEART, CardNumber.TWO),
                        new Card(Suit.HEART, CardNumber.FIVE),
                        List.of(new Card(Suit.CLOVER, CardNumber.TWO),
                                new Card(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어가 패배한다.
                        // 플레이어
                        new Card(Suit.DIAMOND, CardNumber.TWO),
                        new Card(Suit.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(Suit.SPADE, CardNumber.TWO),
                                new Card(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(Suit.HEART, CardNumber.TWO),
                        new Card(Suit.HEART, CardNumber.FIVE),
                        List.of(new Card(Suit.CLOVER, CardNumber.THREE),
                                new Card(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어 딜러 모두 무승부한다.
                        // 플레이어
                        new Card(Suit.DIAMOND, CardNumber.TWO),
                        new Card(Suit.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(Suit.SPADE, CardNumber.TWO),
                                new Card(Suit.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(Suit.HEART, CardNumber.TWO),
                        new Card(Suit.HEART, CardNumber.FIVE),
                        List.of(new Card(Suit.CLOVER, CardNumber.TWO),
                                new Card(Suit.CLOVER, CardNumber.KING)),
                        // 딜러 무승부
                        ResultType.PUSH
                )
        );
    }
}
