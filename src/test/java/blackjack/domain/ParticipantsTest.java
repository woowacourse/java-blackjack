package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

@Nested
public class ParticipantsTest {

    @Nested
    @DisplayName("플레이어 여러명 생성 테스트")
    class CreateParticipantsTest {

        @Test
        @DisplayName("2명 이상의 플레이어를 입력 받을 수 있다.")
        void createParticipantsByNames() {
            List<String> names = List.of("hula", "sana", "jason");
            Participants participants = Participants.createParticipantsByNames(names);

            assertThat(participants.size()).isEqualTo(4);
        }
    }
}
