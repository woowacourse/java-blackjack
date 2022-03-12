package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.ParticipantName;

class ParticipantNameTest {

    @Test
    @DisplayName("이름으로 null을 받았을 경우 오류")
    void createPlayerNullNameFail() {
        assertThatThrownBy(() -> {
            new ParticipantName(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름으로 Empty 값을 받았을 경우 오류")
    void createPlayerEmptyNameFail() {
        assertThatThrownBy(() -> {
            new ParticipantName("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}