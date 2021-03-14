package blackjack.domain.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static blackjack.domain.WinOrLose.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class ResultOfGamerTest {

    private ResultOfGamer resultOfGamer;

    @BeforeEach
    void init() {
        resultOfGamer = new ResultOfGamer("pobi", WIN, 1000);
    }


    @Test
    void getName() {
        assertThat(resultOfGamer.getName()).isEqualTo("pobi");
    }

    @Test
    void getWinOrLose() {
        assertThat(resultOfGamer.getWinOrLose()).isEqualTo(WIN);
    }

    @Test
    void getWinOrLoseAsString() {
        assertThat(resultOfGamer.getWinOrLoseAsString()).isEqualTo("승");
    }

    @Test
    void getRevenue() {
        assertThat(resultOfGamer.getRevenue()).isEqualTo(1000);
    }
}