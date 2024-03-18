package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerBetAmountsTest {

    @DisplayName("베팅내역을 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> new PlayerBetAmounts(Map.of(new Name("이상"), new BetAmount(1000))))
                .doesNotThrowAnyException();
    }
}
