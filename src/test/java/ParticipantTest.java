import domain.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParticipantTest {
    @Test
    void 참가자_이름은_2자_이상이어야_한다() {
        // given
        String name = "a";

        // when & then
        Assertions.assertThatThrownBy(() -> new Participant(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 참가자_이름은_10자_이하여야_한다() {
        // given
        String name = "12345678901";

        // when & then
        Assertions.assertThatThrownBy(() -> new Participant(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
