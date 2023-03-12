package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @ParameterizedTest
    @MethodSource("isHittableDummy")
    @DisplayName("플레이어가 카드를 뽑을 수 있는지 확인한다.")
    void isHittable(final List<Card> initialCards, final List<Card> additionalCards, final boolean expected) {
        Participant player = new Player(ParticipantCardsFixture.createParticipantsCards(initialCards, additionalCards)
                , "베로", 1000);

        assertThat(player.isHittable()).isEqualTo(expected);
    }

    static Stream<Arguments> isHittableDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 히트 가능
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                                Card.of(Suit.DIAMOND, CardNumber.FOUR)),
                        List.of(
                                Card.of(Suit.DIAMOND, CardNumber.THREE),
                                Card.of(Suit.HEART, CardNumber.FOUR)
                        ), true),
                Arguments.arguments(
                        // 히트 가능
                        List.of(Card.of(Suit.DIAMOND, CardNumber.TWO),
                                Card.of(Suit.DIAMOND, CardNumber.THREE)),
                        List.of(
                                Card.of(Suit.SPADE, CardNumber.ACE),
                                Card.of(Suit.CLOVER, CardNumber.FOUR)
                        ), true),
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
}
