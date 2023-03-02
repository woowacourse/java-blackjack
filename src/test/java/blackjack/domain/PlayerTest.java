package blackjack.domain;

import blackjack.fixture.ParticipantCardsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 뽑는다.")
    void hit() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant player = new Player(ParticipantCardsFixture.createParticipantsCards(cardOne, cardTwo, List.of()));
        int beforeHitPoint = player.getTotalPoint();
        player.hit(new Card(CardShape.SPADE, CardNumber.ACE));
        int afterHitPoint = player.getTotalPoint();

        assertThat(afterHitPoint).isGreaterThan(beforeHitPoint);
    }

    @Test
    @DisplayName("플레이어가 카드를 보여준다.")
    void open() {
        Card cardOne = new Card(CardShape.DIAMOND, CardNumber.THREE);
        Card cardTwo = new Card(CardShape.DIAMOND, CardNumber.TWO);
        Participant player = new Player(ParticipantCardsFixture.createParticipantsCards(cardOne, cardTwo, List.of()));

        assertThat(player.open(2)).containsAll(List.of(cardOne, cardTwo));
    }

    @ParameterizedTest
    @MethodSource("isHittableDummy")
    @DisplayName("플레이어가 카드를 뽑을 수 있는지 확인한다.")
    void isHittable(final Card cardOne, final Card cardTwo, final List<Card> additionalCards, final boolean expected) {
        Participant player = new Player(ParticipantCardsFixture.createParticipantsCards(cardOne, cardTwo, additionalCards));

        assertThat(player.isHittable()).isEqualTo(expected);
    }

    static Stream<Arguments> isHittableDummy() {
        return Stream.of(
                Arguments.arguments(
                        // 히트 가능
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.FOUR),
                        List.of(
                                new Card(CardShape.DIAMOND, CardNumber.THREE),
                                new Card(CardShape.HEART, CardNumber.FOUR)
                        ), true),
                Arguments.arguments(
                        // 히트 가능
                        new Card(CardShape.DIAMOND, CardNumber.TWO),
                        new Card(CardShape.DIAMOND, CardNumber.THREE),
                        List.of(
                                new Card(CardShape.SPADE, CardNumber.ACE),
                                new Card(CardShape.CLOVER, CardNumber.FOUR)
                        ), true),
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
}