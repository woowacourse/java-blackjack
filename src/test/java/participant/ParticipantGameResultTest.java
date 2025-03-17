package participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.blackjackgame.ParticipantGameResult;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ParticipantGameResultTest {

    @Test
    void 카드와_금액으로_얻는_금액을_계산할_수_있다() {
        int money = 1000;
        ParticipantGameResult participantGameResult = ParticipantGameResult.of(21, 21, false);
        assertThat(participantGameResult.calculateEarnMoney(money)).isEqualTo(money);
    }
}
