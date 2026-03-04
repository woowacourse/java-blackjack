import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParticipantTest {

    @Test
    void 참여자는_받은_카드를_자신의_카드_패에_추가한다() {
        //given
        Participant participant = Participant.from("pobi");
        Card card = Card.of("스페이드", 1);

        //when
        List<Card> drawn = participant.draw(card);

        //then
        assertThat(drawn.getLast()).isEqualTo(card);
    }
}
