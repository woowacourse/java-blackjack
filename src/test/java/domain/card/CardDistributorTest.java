package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDistributorTest {

    @Test
    void distribute() {
        CardDistributor cardDistributor = new CardDistributor();
        assertThatNoException().isThrownBy(cardDistributor::distribute);
    }

    @Test
    void failed() {
        CardDistributor cardDistributor = new CardDistributor();
        for (int i = 0; i < 52; i++) {
            cardDistributor.distribute();
        }
        assertThatThrownBy(cardDistributor::distribute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 카드가 모두 소요됐습니다.");
    }
}
