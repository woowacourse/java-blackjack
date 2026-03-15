package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dto.ParticipantsInitDTO;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.Money;

public class ParticipantsTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    @DisplayName("참가자 수가 16명인 경우, 정상 동작한다.")
    void 참가자_수_정상_범위_테스트() {
        // given
        List<ParticipantsInitDTO> participantsInitDTOS = createParticipantsDTOs(16);

        // when & then
        assertThatCode(() -> new Participants(participantsInitDTOS))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("참가자 수가 17명인 경우, IllegalArgumentException이 발생한다.")
    void 참가자_수_초과_예외_테스트() {
        // given
        List<ParticipantsInitDTO> participantsInitDTOS = createParticipantsDTOs(17);

        // when & then
        assertThatThrownBy(() -> new Participants(participantsInitDTOS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    private List<ParticipantsInitDTO> createParticipantsDTOs(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new ParticipantsInitDTO("User" + i, new Money(1000)))
                .toList();
    }
}
