package domain.game;

import domain.player.Participant;
import domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BetAmountsTest {
    private BetAmounts betAmounts;
    
    @BeforeEach
    void setUp() {
        betAmounts = new BetAmounts();
    }
    
    @Test
    @DisplayName("저장된 플레이어의 배팅 금액이 해당 플레이어와 매칭되어있다.")
    void saveBetAmount() {
        Player abel = new Participant("abel");
        betAmounts.savePlayerBetAmount(abel, 1000);
        
        assertThat(betAmounts.findBetAmountByPlayer(abel)).isEqualTo(1000);
    }
}