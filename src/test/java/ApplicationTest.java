import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    void 구분자만_있는_입력이_입력되면_예외를_발생한다() {
        //given
        String input = ",,,,";

        //when


        //then

    }

    @Test
    void 정상적인_입력_시_참가자를_생성한다() {
        //given
        String input = "pobi";

        //when
        Participant from = Participant.from(input);

        //then
        assertThat(from.getName()).isEqualTo(input);
    }
}