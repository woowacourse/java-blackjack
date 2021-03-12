package blackjack.domain.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.WinOrLose.LOSE;
import static blackjack.domain.WinOrLose.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class ResultOfDealerTest {

    private ResultOfDealer resultOfDealer;

    @BeforeEach
    void init() {
        resultOfDealer = new ResultOfDealer(
                Arrays.asList(WIN, WIN, LOSE), 1000
        );
    }

    @Test
    void getWinOrLosesAsString() {
        List<String> winOrLosesAsString = resultOfDealer.getWinOrLosesAsString();

        assertThat(winOrLosesAsString).containsExactly("승", "승", "패");
    }

    @Test
    void getRevenue() {
        assertThat(resultOfDealer.getRevenue()).isEqualTo(1000);
    }
}