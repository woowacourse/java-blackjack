import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    @DisplayName("요청한 카드 저장 확인 테스트")
    void shouldSuccessTakeCard() {
        Dealer dealer = new Dealer();
        dealer.takeCard(new Card("10다이아몬드", 10));
        dealer.takeCard(new Card("3다이아몬드", 3));
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }
}
