package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들의 이름은 중복이 없어야 한다.")
    public void validateOverlappedNames() {
        List<Participant> participantGroup = Arrays.asList(
            new Participant("jason"),
            new Participant("jason")
        );

        assertThatCode(() -> {
            new Participants(participantGroup);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("참가자들의 이름은 중복이 없어야 합니다.");
    }
}
