package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.view.ParticipantAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("참가자 행동 테스트")
class ParticipantActionTest {

    @DisplayName("참가자 행동이 HIT인지 확인한다.")
    @ParameterizedTest
    @CsvSource({
            "HIT, true",
            "STAND, false"
    })
    void isHitTest(ParticipantAction participantAction, boolean expected) {
        // when
        boolean isHit = participantAction.isHit();

        // then
        assertThat(isHit)
                .isEqualTo(expected);
    }
}
