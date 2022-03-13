package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ResultTest {
    @Test
    void getResultFromInt() {
        Result win = Result.of(2, 1);
        Result draw = Result.of(2, 2);
        Result lose = Result.of(1, 2);

        assertThat(win).isEqualTo(Result.WIN);
        assertThat(draw).isEqualTo(Result.DRAW);
        assertThat(lose).isEqualTo(Result.LOSE);
    }

    @ParameterizedTest(name = "{0} vs {1} is {2}")
    @CsvSource(value = {"BLACKJACK,BUST,WIN",
            "BLACKJACK,STAND,WIN",
            "STAND,BUST,WIN",
            "BLACKJACK,BLACKJACK,DRAW",
            "STAND,STAND,DRAW",
            "BUST,BUST,DRAW",
            "BUST,STAND,LOSE",
            "STAND,BLACKJACK,LOSE",
    })
    void getResultFromStatus(Status status1, Status status2, Result expected) {
        Result actual = Result.of(status1, status2);

        assertThat(actual).isEqualTo(expected);
    }
}
