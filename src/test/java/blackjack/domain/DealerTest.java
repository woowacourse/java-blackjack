package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.strategy.NumberGenerator;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("카드 나눠주는 기능 테스트")
    @Test
    void handOut() {
        Dealer dealer = new Dealer();
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
        Card card = dealer.handOutCard(numberGenerator);

        assertThat(card.getName()).isEqualTo("3다이아몬드");
    }
}
