package blackjack.card.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameResultTest {

    @DisplayName("GameResult에서 값(승,무,패) 찾기")
    @ParameterizedTest
    @CsvSource(value = {"-1,LOSE", "0,DRAW", "1,WIN"})
    void findByResult(int input, GameResult result) {
        GameResult expect = GameResult.findByResult(input);

        assertThat(expect).isEqualTo(result);
    }

    @DisplayName("GameResult 잘못된 입력시 Exception")
    @Test
    void findByResultException() {
        int wrong = 10;

        assertThatThrownBy(() -> GameResult.findByResult(wrong))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d 는 존재하지 않는 결과 값 입니다.", wrong);
    }
}