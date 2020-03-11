package user;

import card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantTest {
    private Participant participant;
    private Deck deck;

    @BeforeEach
    void setUp() {
        ParticipantName name = new ParticipantName("또링");
        deck = new Deck();
        participant = new Participant(name, deck);
    }

    @DisplayName("생성했을 때, 두 장의 카드를 보유하고 있는지 확인")
    @Test
    void constructor_InitializeParticipant_HandsSizeIsTwo() {
        assertThat(participant.handSize()).isEqualTo(2);
    }

    @DisplayName("카드를 더 받을지 입력받을 때 y혹은 n이 입력되지 않았을 경우 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "1", "q"})
    void checkAnswer_InvalidAnswer_ExceptionThrown(String answer) {
        assertThatThrownBy(() -> participant.needMoreCard(answer, deck))
                .isInstanceOf(InvalidParticipantException.class)
                .hasMessage(InvalidParticipantException.INVALID_INPUT);
    }
}
