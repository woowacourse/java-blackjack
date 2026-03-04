import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    @DisplayName("일반 카드들의 합산 점수를 계산한다")
    void calculateBasicScore() {
        //given, when
        int score = gameService.calculateScore(List.of("2", "5", "10"));
        //then
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("페이스 카드가 포함된 점수를 계산한다")
    void calculateAceAsEleven() {
        // given, when
        int score = gameService.calculateScore(List.of("1", "J", "Q", "K"));
        //then
        assertThat(score).isEqualTo(31);
    }

    @Test
    @DisplayName("첫 배부 때 합이 21 나오면 블랙잭 판정이다")
    public void if_card_sum_equals21_blackjack(){
        int score = 21;
        boolean result = gameService.isBlackjack(score);
        assertThat(result).isTrue();
    }
}
