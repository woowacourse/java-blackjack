package user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantNameTest {
    @DisplayName("생성자에 null이나 빈 문자열이 입력되었을 경우 예외처리")
    @ParameterizedTest
    @NullAndEmptySource
    void checkNullOrEmpty_NullOrEmpty_ExceptionThrown(String name) {
        assertThatThrownBy(() -> new ParticipantName(name))
                .isInstanceOf(InvalidParticipantNameException.class)
                .hasMessage(InvalidParticipantNameException.NULL_OR_EMPTY);
    }

    @DisplayName("생성자에 '딜러'가 입력되었을 경우 예외처리")
    @Test
    public void checkSameAsDealer_Dealer_ExceptionThrown() {
        assertThatThrownBy(() -> new ParticipantName("딜러"))
                .isInstanceOf(InvalidParticipantNameException.class)
                .hasMessage(InvalidParticipantNameException.SAME_AS_DEALER);
    }
}
