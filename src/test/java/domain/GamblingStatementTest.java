package domain;

import domain.participant.Participant;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamblingStatementTest {

    @Test
    void 생성_확인() {
        //given
        Map<Participant, Money> originGamblingStatement = new HashMap<>();

        //when
        GamblingStatement gamblingStatement = new GamblingStatement(originGamblingStatement);

        //then
        Assertions.assertThat(gamblingStatement).isInstanceOf(GamblingStatement.class);
    }
}
