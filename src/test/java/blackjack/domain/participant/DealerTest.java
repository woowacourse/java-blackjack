package blackjack.domain.participant;

import blackjack.domain.card.*;
import blackjack.domain.game.ParticipantCards;
import blackjack.domain.game.ResultType;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class DealerTest {
    @Test
    @DisplayName("생성한다.")
    void create() {
        final Deck deck = new CardDeck();
        final ParticipantCards participantCards = new ParticipantCards(deck);

        assertThatNoException().isThrownBy(() -> new Dealer(participantCards));
    }

    @ParameterizedTest
    @MethodSource("isHittableDummy")
    @DisplayName("딜러가 카드를 뽑을 수 있는지 확인한다.")
    void isHittable(final Card cardOne, final Card cardTwo, final List<Card> additionalCards, final boolean expected) {
        Participant player = new Dealer(ParticipantCardsFixture.create(cardOne, cardTwo, additionalCards));

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
        ParticipantCards participantsCards = ParticipantCardsFixture.create(playerCard1, playerCard2, playerAdditionalCards);
        ParticipantCards dealerCards = ParticipantCardsFixture.create(dealerCard1, dealerCard2, dealerAdditionalCards);

        Player player = new Player(participantsCards, "베로");
        Dealer dealer = new Dealer(dealerCards);

        assertThat(dealer.judgeResult(player)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> isHittableDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 히트 가능
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.THREE),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.THREE),
                                new Card(CardShape.HEART, CardNumber.EIGHT)
                        ), true),
                Arguments.arguments(
                        // 히트 불가능
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.THREE),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.ACE),
                                new Card(CardShape.CLOVER, CardNumber.FOUR)
                        ), false),
                Arguments.arguments(
                        // 히트 불가능
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FOUR),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.ACE),
                                new Card(CardShape.CLOVER, CardNumber.QUEEN),
                                new Card(CardShape.HEART, CardNumber.JACK),
                                new Card(CardShape.DIAMOND, CardNumber.THREE)
                        ), false)
        );
    }

    static Stream<Arguments> judgeResultDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 플레이어가 승리한다.
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.THREE),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.TWO),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 승리
                        ResultType.WIN
                ),
                Arguments.arguments(
                        // 플레이어 딜러 모두 버스트하는 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(CardShape.SPADE, CardNumber.JACK),
                                new Card(CardShape.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.QUEEN),
                        List.of(new Card(CardShape.CLOVER, CardNumber.JACK),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어만 버스트하는 경우
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.NINE),
                        new Card(CardShape.DIAMOND, CardNumber.QUEEN),
                        List.of(new Card(CardShape.SPADE, CardNumber.JACK),
                                new Card(CardShape.HEART, CardNumber.KING)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어가 패배한다.
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.TWO),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.THREE),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어 딜러은 점수가 동일하고 블랙잭이 아닐 경우 플레이어가 패배한다.
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.TWO),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.TWO),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어 패배
                        ResultType.LOSE
                ),
                Arguments.arguments(
                        // 플레이어 딜러 모두 블랙잭일 떄 무승부한다.
                        // 플레이어
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FIVE),
                        List.of(new Card(CardShape.SPADE, CardNumber.FOUR),
                                new Card(CardShape.HEART, CardNumber.QUEEN)),
                        // 딜러
                        new Card(CardShape.HEART, CardNumber.TWO),
                        new Card(CardShape.HEART, CardNumber.FIVE),
                        List.of(new Card(CardShape.CLOVER, CardNumber.FOUR),
                                new Card(CardShape.CLOVER, CardNumber.KING)),
                        // 플레이어
                        ResultType.PUSH
                )
        );
    }
}
