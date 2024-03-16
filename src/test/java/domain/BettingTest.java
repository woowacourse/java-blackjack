package domain;

import domain.participant.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BettingTest {
    @DisplayName("플레이어 이름에 맞는 배팅 금액을 반환한다.")
    @Test
    void getBettingTest() {
        // Given
        PlayerName test = new PlayerName("test");
        Map<PlayerName, BettingAmount> initialBetting = new HashMap<>();
        initialBetting.put(test, new BettingAmount("15000"));
        Betting betting = new Betting(initialBetting);

        // When
        BettingAmount bettingAmount = betting.getBetting(test);
        int amount = bettingAmount.getAmount();

        // Then
        assertThat(amount).isEqualTo(15000);
    }
}
