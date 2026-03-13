import static org.assertj.core.api.Assertions.assertThat;

import model.participant.Participant;
import model.participant.Player;
import model.vo.BetAmount;
import org.junit.jupiter.api.Test;

class ApplicationTest {
    @Test
    void 정상적인_입력_시_참가자를_생성한다() {
        //given
        String input = "pobi";

        //when
        Participant from = Player.of(input, BetAmount.of(10000));

        //then
        assertThat(from.getName()).isEqualTo(input);
    }
}
