package domain.game;

import domain.participant.Player;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EarningResultTest {
    @Test
    void 딜러의_최종수익을_계산() {
        EarningResult result = new EarningResult(Map.of(
            new Player("1"), 1000.0,
                new Player("2"), 0.0,
                new Player("3"), -2000.0
        ));

        double dealerEarning = result.calcualteDealerEarning();
        assertThat(dealerEarning).isEqualTo(1000.0);
    }
}