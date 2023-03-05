package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {
    private Participant participant;

    private static Stream<Arguments> cardProvider() {
        return Stream.of(
                Arguments.of(new Card("10클로버", 10), true),
                Arguments.of(new Card("9하트", 9), false)
        );
    }

    @BeforeEach
    void setUp() {
        participant = new Participant(
                new ArrayList<>(List.of(
                        new Card("2하트", 2),
                        new Card("K다이아", 10))),
                "Leo");
    }

    @Test
    void receiveCard() {
        participant.receiveCard(new Card("10클로버", 10));
        assertThat(participant.getCardNames().size()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("cardProvider")
    @DisplayName("현재 참가자 패의 합이 21초과 여부에 따라 boolean을 반환한다.")
    void isBustTest(Card card, boolean isBust) {
        participant.receiveCard(card);
        assertThat(participant.isBust()).isEqualTo(isBust);
    }

    @Test
    void getHandValue() {
        assertThat(participant.getHandValue()).isEqualTo(12);
    }
}
