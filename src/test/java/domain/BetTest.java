package domain;

import domain.game.Bet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetTest {
    
    @Test
    @DisplayName("Bet 생성 테스트")
    void create() {
        Bet bet = new Bet(1000);
        Assertions.assertThat(bet.getBet()).isEqualTo(1000);
    }
}